package dev.alex.content_calendar.repository;

import dev.alex.content_calendar.model.Content;
import dev.alex.content_calendar.model.Status;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ContentRepository extends ListCrudRepository<Content, Integer> {
    List<Content> findAllByTitleContainsIgnoreCaseOrDescriptionContainsIgnoreCase(String keyword, String keyword2);

    @Query("SELECT * FROM content WHERE status = :status")
    List<Content> listByStatus(@Param("status") Status status);
}
