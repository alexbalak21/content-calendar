package dev.alex.content_calendar.repository;

import dev.alex.content_calendar.model.Content;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface ContentRepository extends ListCrudRepository<Content, Integer> {
    List<Content> findAllByTitleContainsIgnoreCaseOrDescriptionContainsIgnoreCase(String keyword, String keyword2);

}
