# RAG Overview

RAG means Retrieval-Augmented Generation.

The retrieval step searches a knowledge base for the most relevant chunks of text for a user question.

The generation step turns that retrieved context into a useful answer.

This sample uses TF-IDF vectorization and cosine similarity for retrieval. That is a simple baseline that is easy to study.

In a production system, the generation step would usually be handled by a large language model.