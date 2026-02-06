package com.example.enterpriserag.retrieval;

import jakarta.validation.constraints.NotBlank;

public record RagQueryRequest(@NotBlank String tenantId, @NotBlank String query) {
}
