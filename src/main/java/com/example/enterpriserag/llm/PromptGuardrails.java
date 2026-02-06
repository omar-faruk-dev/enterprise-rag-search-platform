package com.example.enterpriserag.llm;

import org.springframework.stereotype.Component;

@Component
public class PromptGuardrails {

    public String sanitize(String prompt) {
        return prompt
                .replaceAll("(?i)ignore previous instructions", "[FILTERED]")
                .replaceAll("(?i)drop table", "[FILTERED]");
    }

    public String baseSystemPrompt() {
        return "You are an enterprise retrieval assistant. Answer strictly from supplied context and include uncertainty.";
    }
}
