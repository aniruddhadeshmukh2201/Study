from __future__ import annotations

import argparse
import math
import re
from dataclasses import dataclass
from collections import Counter
from pathlib import Path
from typing import Iterable


KB_DIR = Path(__file__).parent / "knowledge_base"


@dataclass(frozen=True)
class Chunk:
    text: str
    source: str
    chunk_index: int


def load_documents(folder: Path) -> list[tuple[str, str]]:
    documents: list[tuple[str, str]] = []
    for path in sorted(folder.glob("*")):
        if path.suffix.lower() not in {".txt", ".md"}:
            continue
        content = path.read_text(encoding="utf-8").strip()
        if content:
            documents.append((path.name, content))
    return documents


def chunk_text(text: str, *, max_words: int = 120, overlap: int = 24) -> list[str]:
    words = text.split()
    if not words:
        return []

    if overlap >= max_words:
        raise ValueError("overlap must be smaller than max_words")

    chunks: list[str] = []
    step = max_words - overlap
    for start in range(0, len(words), step):
        chunk_words = words[start : start + max_words]
        if not chunk_words:
            break
        chunks.append(" ".join(chunk_words))
    return chunks


def build_chunks(documents: Iterable[tuple[str, str]]) -> list[Chunk]:
    chunks: list[Chunk] = []
    for source_name, content in documents:
        for chunk_index, chunk_text_value in enumerate(chunk_text(content), start=1):
            chunks.append(Chunk(text=chunk_text_value, source=source_name, chunk_index=chunk_index))
    return chunks


def tokenize(text: str) -> list[str]:
    return re.findall(r"[a-zA-Z]{2,}", text.lower())


def build_index(chunks: list[Chunk]) -> tuple[dict[str, float], list[dict[str, float]], list[float]]:
    if not chunks:
        raise ValueError("No content found in the knowledge base.")

    tokenized_documents = [tokenize(chunk.text) for chunk in chunks]
    document_count = len(tokenized_documents)

    document_frequency: Counter[str] = Counter()
    for tokens in tokenized_documents:
        document_frequency.update(set(tokens))

    inverse_document_frequency: dict[str, float] = {
        term: math.log((1 + document_count) / (1 + frequency)) + 1.0
        for term, frequency in document_frequency.items()
    }

    document_vectors: list[dict[str, float]] = []
    document_norms: list[float] = []

    for tokens in tokenized_documents:
        term_frequency = Counter(tokens)
        token_count = len(tokens) or 1

        vector: dict[str, float] = {}
        for term, frequency in term_frequency.items():
            vector[term] = (frequency / token_count) * inverse_document_frequency[term]

        norm = math.sqrt(sum(weight * weight for weight in vector.values()))
        document_vectors.append(vector)
        document_norms.append(norm)

    return inverse_document_frequency, document_vectors, document_norms


def vectorize_query(query: str, inverse_document_frequency: dict[str, float]) -> tuple[dict[str, float], float]:
    tokens = tokenize(query)
    term_frequency = Counter(tokens)
    token_count = len(tokens) or 1

    vector: dict[str, float] = {}
    for term, frequency in term_frequency.items():
        if term in inverse_document_frequency:
            vector[term] = (frequency / token_count) * inverse_document_frequency[term]

    norm = math.sqrt(sum(weight * weight for weight in vector.values()))
    return vector, norm


def cosine_similarity_sparse(
    left_vector: dict[str, float],
    left_norm: float,
    right_vector: dict[str, float],
    right_norm: float,
) -> float:
    if not left_vector or not right_vector or left_norm == 0 or right_norm == 0:
        return 0.0

    if len(left_vector) > len(right_vector):
        left_vector, right_vector = right_vector, left_vector

    dot_product = sum(weight * right_vector.get(term, 0.0) for term, weight in left_vector.items())
    return dot_product / (left_norm * right_norm)


def retrieve(
    query: str,
    chunks: list[Chunk],
    inverse_document_frequency: dict[str, float],
    document_vectors: list[dict[str, float]],
    document_norms: list[float],
    top_k: int = 3,
) -> list[tuple[Chunk, float]]:
    query_vector, query_norm = vectorize_query(query, inverse_document_frequency)

    scores = [
        cosine_similarity_sparse(query_vector, query_norm, document_vectors[index], document_norms[index])
        for index in range(len(chunks))
    ]

    ranked_indexes = sorted(range(len(chunks)), key=lambda index: scores[index], reverse=True)
    results: list[tuple[Chunk, float]] = []
    for index in ranked_indexes[:top_k]:
        results.append((chunks[index], float(scores[index])))
    return results


def extract_best_sentences(question: str, retrieved_chunks: list[tuple[Chunk, float]]) -> list[str]:
    question_keywords = set(re.findall(r"[a-zA-Z]{3,}", question.lower()))
    scored_sentences: list[tuple[int, str]] = []

    for chunk, _score in retrieved_chunks:
        sentences = re.split(r"(?<=[.!?])\s+", chunk.text)
        for sentence in sentences:
            sentence_keywords = set(re.findall(r"[a-zA-Z]{3,}", sentence.lower()))
            overlap = len(question_keywords & sentence_keywords)
            if overlap:
                scored_sentences.append((overlap, sentence.strip()))

    scored_sentences.sort(key=lambda item: (-item[0], len(item[1])))
    seen: set[str] = set()
    best_sentences: list[str] = []

    for _score, sentence in scored_sentences:
        normalized_sentence = sentence.lower()
        if normalized_sentence in seen:
            continue
        seen.add(normalized_sentence)
        best_sentences.append(sentence)
        if len(best_sentences) == 3:
            break

    return best_sentences


def synthesize_answer(question: str, retrieved_chunks: list[tuple[Chunk, float]]) -> str:
    if not retrieved_chunks:
        return "I could not find any relevant context in the knowledge base."

    best_sentences = extract_best_sentences(question, retrieved_chunks)
    if len(best_sentences) < 2:
        top_chunk_sentences = re.split(r"(?<=[.!?])\s+", retrieved_chunks[0][0].text)
        for sentence in top_chunk_sentences:
            cleaned_sentence = sentence.strip()
            if cleaned_sentence and cleaned_sentence not in best_sentences:
                best_sentences.append(cleaned_sentence)
            if len(best_sentences) == 2:
                break

    if best_sentences:
        bullet_points = "\n".join(f"- {sentence}" for sentence in best_sentences)
        return (
            "Based on the retrieved context, here is a concise answer:\n"
            f"{bullet_points}\n\n"
            "This answer is generated from the most relevant sentences in the knowledge base."
        )

    summary_parts = [chunk.text for chunk, _score in retrieved_chunks[:2]]
    summary = " ".join(summary_parts)
    summary = summary[:500].rstrip()
    if len(summary_parts) > 0 and len(summary) == 500:
        summary += "..."

    return (
        "Based on the retrieved context, I found these useful passages:\n"
        f"{summary}\n\n"
        "The sample uses retrieval plus a simple synthesis step instead of a large language model."
    )


def run_question_answering(question: str, top_k: int) -> None:
    documents = load_documents(KB_DIR)
    chunks = build_chunks(documents)
    inverse_document_frequency, document_vectors, document_norms = build_index(chunks)
    retrieved_chunks = retrieve(
        question,
        chunks,
        inverse_document_frequency,
        document_vectors,
        document_norms,
        top_k=top_k,
    )

    print("\nQuestion:")
    print(question)

    print("\nTop retrieved chunks:")
    for rank, (chunk, score) in enumerate(retrieved_chunks, start=1):
        print(f"{rank}. {chunk.source} | chunk {chunk.chunk_index} | score={score:.4f}")
        print(f"   {chunk.text}\n")

    print("Answer:")
    print(synthesize_answer(question, retrieved_chunks))


def main() -> None:
    parser = argparse.ArgumentParser(description="Sample RAG demo for Python practice")
    parser.add_argument("--question", type=str, help="Question to ask the knowledge base")
    parser.add_argument("--top-k", type=int, default=3, help="How many chunks to retrieve")
    args = parser.parse_args()

    if not KB_DIR.exists():
        raise FileNotFoundError(f"Knowledge base folder not found: {KB_DIR}")

    question = args.question
    if not question:
        question = input("Enter a question: ").strip()

    if not question:
        raise ValueError("Question cannot be empty.")

    run_question_answering(question, top_k=args.top_k)


if __name__ == "__main__":
    main()