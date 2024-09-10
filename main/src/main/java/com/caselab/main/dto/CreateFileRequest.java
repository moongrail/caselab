package com.caselab.main.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record CreateFileRequest(
        @NotNull
        @NotBlank
        String title,
        @JsonProperty("creation_date")
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        @NotNull
        LocalDateTime creationDate,
        String description,
        @NotNull
        @NotBlank
        String content
) {
}
