package com.hassan.careercoachai.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record ChatRequest(
        @NotNull UUID conversationId,
        @NotBlank @Size(max = 10_000) String message
) {}