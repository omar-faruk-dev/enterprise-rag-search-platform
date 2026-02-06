package com.example.enterpriserag.retrieval;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import com.example.enterpriserag.llm.LlmClient;
import com.example.enterpriserag.llm.PromptGuardrails;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class RagOrchestrationServiceTest {

    @Mock
    private ChunkRepository chunkRepository;

    @Mock
    private LlmClient llmClient;

    @InjectMocks
    private RagOrchestrationService service;

    private final PromptGuardrails guardrails = new PromptGuardrails();

    @Test
    void shouldReturnAnswerAndCitations() {
        ChunkRecord record = new ChunkRecord();
        record.setTenantId("gov-1");
        record.setChunkText("Policy states data is retained for 7 years.");
        record.setEmbeddingRef("e1");
        when(chunkRepository.findTop5ByTenantIdAndChunkTextContainingIgnoreCase("gov-1", "retention"))
                .thenReturn(List.of(record));
        when(llmClient.complete(anyString(), anyString())).thenReturn("Retention is 7 years.");

        RagOrchestrationService sut = new RagOrchestrationService(chunkRepository, llmClient, guardrails);
        RagQueryResponse response = sut.answer(new RagQueryRequest("gov-1", "retention"));

        assertThat(response.answer()).contains("7 years");
        assertThat(response.citations()).hasSize(1);
    }
}
