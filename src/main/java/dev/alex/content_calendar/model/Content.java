package dev.alex.content_calendar.model;

import java.time.LocalDate;

public record Content(
    Integer id, 
    String title, 
    String description,
    Status status,
    Type ContentType,
    LocalDate dateCreated,
    LocalDate dateUpdated,
    String url
) {}
