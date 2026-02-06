package com.example.enterpriserag.retrieval;

import java.util.List;

public record RagQueryResponse(String answer, List<String> citations) {
}
