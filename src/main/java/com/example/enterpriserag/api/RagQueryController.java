package com.example.enterpriserag.api;

import com.example.enterpriserag.retrieval.RagOrchestrationService;
import com.example.enterpriserag.retrieval.RagQueryRequest;
import com.example.enterpriserag.retrieval.RagQueryResponse;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/rag")
public class RagQueryController {

    private final RagOrchestrationService ragOrchestrationService;

    public RagQueryController(RagOrchestrationService ragOrchestrationService) {
        this.ragOrchestrationService = ragOrchestrationService;
    }

    @PostMapping("/query")
    @PreAuthorize("hasAnyRole('ADMIN','ANALYST','AUDITOR')")
    public RagQueryResponse query(@Valid @RequestBody RagQueryRequest request) {
        return ragOrchestrationService.answer(request);
    }
}
