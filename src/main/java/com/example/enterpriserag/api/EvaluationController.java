package com.example.enterpriserag.api;

import com.example.enterpriserag.evaluation.EvaluationRequest;
import com.example.enterpriserag.evaluation.EvaluationResult;
import com.example.enterpriserag.evaluation.EvaluationService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/evaluation")
public class EvaluationController {

    private final EvaluationService evaluationService;

    public EvaluationController(EvaluationService evaluationService) {
        this.evaluationService = evaluationService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public EvaluationResult evaluate(@Valid @RequestBody EvaluationRequest request) {
        return evaluationService.evaluate(request);
    }
}
