package com.hassan.careercoachai.dto;

import java.util.UUID;

public record CvUploadResponse(
        UUID conversationId,
        String message
) {}