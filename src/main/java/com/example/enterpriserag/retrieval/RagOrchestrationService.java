package com.example.enterpriserag.retrieval;

import com.example.enterpriserag.llm.LlmClient;
import com.example.enterpriserag.llm.PromptGuardrails;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class RagOrchestrationService {

    private final ChunkRepository chunkRepository;
    private final LlmClient llmClient;
    private final PromptGuardrails promptGuardrails;

    public RagOrchestrationService(ChunkRepository chunkRepository,
                                   LlmClient llmClient,
                                   PromptGuardrails promptGuardrails) {
        this.chunkRepository = chunkRepository;
        this.llmClient = llmClient;
        this.promptGuardrails = promptGuardrails;
    }

    public RagQueryResponse answer(RagQueryRequest request) {
        List<ChunkRecord> retrieved = chunkRepository
                .findTop5ByTenantIdAndChunkTextContainingIgnoreCase(request.tenantId(), request.query());
        String context = retrieved.stream().map(ChunkRecord::getChunkText).reduce("", (a, b) -> a + "\n---\n" + b);
        String sanitized = promptGuardrails.sanitize(request.query());
        String answer = llmClient.complete(promptGuardrails.baseSystemPrompt(), "Context:\n" + context + "\nQuestion:" + sanitized);
        List<String> citations = retrieved.stream().map(r -> "chunk:" + r.getId()).toList();
        return new RagQueryResponse(answer, citations);
    }
}
