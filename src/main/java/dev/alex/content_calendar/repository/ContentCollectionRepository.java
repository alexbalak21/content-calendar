package dev.alex.content_calendar.repository;

import dev.alex.content_calendar.model.Content;
import org.springframework.stereotype.Repository;

@Repository
public class ContentCollectionRepository {

    private final List<Content> content = new ArrayList<>();
    public ContentCollectionRepository() {

    }
    public List<Content> findAll() {
        return content;
    }
}
