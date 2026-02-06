package com.example.enterpriserag.api;

import com.example.enterpriserag.ingestion.IngestionRequest;
import com.example.enterpriserag.ingestion.IngestionService;
import jakarta.validation.Valid;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/ingestion")
public class IngestionController {

    private final IngestionService ingestionService;

    public IngestionController(IngestionService ingestionService) {
        this.ingestionService = ingestionService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PreAuthorize("hasAnyRole('ADMIN','ANALYST')")
    public Map<String, Object> ingest(@Valid @RequestBody IngestionRequest request) {
        return Map.of("documentId", ingestionService.ingest(request), "status", "INGESTED");
    }
}
