package com.example.enterpriserag.embedding;

import java.util.List;

public interface EmbeddingPort {
    String store(String tenantId, String text, List<Double> vector);
}
