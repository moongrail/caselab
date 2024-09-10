package com.caselab.main.dto;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record FileResponse(
        Long id,
        String title,
        LocalDateTime creationDate,
        String description,
        String content
) {
}
