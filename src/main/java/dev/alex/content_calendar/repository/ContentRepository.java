package dev.alex.content_calendar.repository;

import dev.alex.content_calendar.model.Content;
import org.springframework.data.repository.ListCrudRepository;

public interface ContentRepository extends ListCrudRepository<Content, Integer> {
}
