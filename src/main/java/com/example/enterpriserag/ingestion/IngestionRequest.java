package com.example.enterpriserag.ingestion;

import jakarta.validation.constraints.NotBlank;

public record IngestionRequest(
        @NotBlank String tenantId,
        @NotBlank String source,
        @NotBlank String classification,
        @NotBlank String content) {
}
