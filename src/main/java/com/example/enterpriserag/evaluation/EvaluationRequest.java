package com.example.enterpriserag.evaluation;

import jakarta.validation.constraints.NotBlank;

public record EvaluationRequest(@NotBlank String expectedKeyword, @NotBlank String actualAnswer) {
}
