package dev.alex.content_calendar.model;

import jakarta.validation.constraints.NotBlank;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;

public record Content(
        @Id
        Integer id,
        @NotBlank
        String title,
        @NotBlank
        String description,
        Status status,
        Type ContentType,
        LocalDate dateCreated,
        LocalDate dateUpdated,
        String url
) {
}

