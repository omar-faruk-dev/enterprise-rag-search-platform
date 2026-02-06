package com.example.enterpriserag.evaluation;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class EvaluationServiceTest {

    private final EvaluationService service = new EvaluationService();

    @Test
    void shouldPassWhenKeywordFound() {
        EvaluationResult result = service.evaluate(new EvaluationRequest("FISMA", "The controls align with FISMA moderate baseline."));
        assertThat(result.pass()).isTrue();
    }
}
