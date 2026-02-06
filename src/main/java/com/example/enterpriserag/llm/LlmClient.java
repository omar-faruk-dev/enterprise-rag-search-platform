package com.example.enterpriserag.llm;

public interface LlmClient {
    String complete(String systemPrompt, String userPrompt);
}
