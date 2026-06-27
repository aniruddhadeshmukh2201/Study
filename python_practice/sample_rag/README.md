# Sample RAG Demo

This folder contains a small Retrieval-Augmented Generation style demo for study and practice.

What it does:

- Loads a few local knowledge-base documents.
- Splits the documents into chunks.
- Vectorizes chunks with a small TF-IDF implementation.
- Retrieves the most relevant chunks for your question.
- Synthesizes a short answer from the retrieved text.

## Install

Create and activate a virtual environment on Windows PowerShell:

```powershell
python -m venv .venv
.venv\Scripts\Activate.ps1
python -m pip install --upgrade pip
```

No extra packages are needed for this version, so there is nothing else to install.

If PowerShell blocks activation, run this once in an elevated or user PowerShell session:

```powershell
Set-ExecutionPolicy -Scope CurrentUser RemoteSigned
```

## Run

Ask a question from the command line:

```powershell
python rag_demo.py --question "What is RAG?"
```

Or run it interactively:

```powershell
python rag_demo.py
```

## How it works

1. The script reads the markdown files in `knowledge_base/`.
2. Each document is split into overlapping chunks.
3. TF-IDF converts chunks and the question into vectors.
4. Cosine similarity ranks the chunks.
5. The answer is synthesized from the best-matching sentences in the retrieved chunks.

This is a simple educational baseline. If you want a more realistic GenAI version later, you can replace the synthesis step with an LLM API call.