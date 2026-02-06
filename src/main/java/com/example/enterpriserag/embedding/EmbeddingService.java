package com.example.enterpriserag.embedding;

import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class EmbeddingService {

    private final EmbeddingPort embeddingPort;

    public EmbeddingService(EmbeddingPort embeddingPort) {
        this.embeddingPort = embeddingPort;
    }

    public String embedAndStore(String tenantId, String chunk) {
        List<Double> deterministicVector = chunk.chars()
                .limit(8)
                .mapToObj(ch -> (double) (ch % 23) / 23)
                .toList();
        return embeddingPort.store(tenantId, chunk, deterministicVector);
    }
}
