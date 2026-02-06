package com.example.enterpriserag.evaluation;

import org.springframework.stereotype.Service;

@Service
public class EvaluationService {

    public EvaluationResult evaluate(EvaluationRequest request) {
        boolean contains = request.actualAnswer().toLowerCase().contains(request.expectedKeyword().toLowerCase());
        double score = contains ? 0.95 : 0.25;
        return new EvaluationResult(score, score >= 0.8);
    }
}
