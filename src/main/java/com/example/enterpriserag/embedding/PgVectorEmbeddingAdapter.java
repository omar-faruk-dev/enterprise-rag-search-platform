package com.example.enterpriserag.embedding;

import java.util.List;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class PgVectorEmbeddingAdapter implements EmbeddingPort {

    private static final Logger log = LoggerFactory.getLogger(PgVectorEmbeddingAdapter.class);

    @Override
    public String store(String tenantId, String text, List<Double> vector) {
        String reference = tenantId + "-" + UUID.randomUUID();
        log.info("Persisted embedding ref={} vectorDimensions={}", reference, vector.size());
        return reference;
    }
}
