package com.hassan.careercoachai.dto;

import java.util.UUID;

public record ChatResponse(
        UUID conversationId,
        String answer
) {}