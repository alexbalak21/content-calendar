package dev.alex.content_calendar.repository;

import dev.alex.content_calendar.model.Content;
import dev.alex.content_calendar.model.Status;
import dev.alex.content_calendar.model.Type;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ContentCollectionRepository {

    private final List<Content> contentList = new ArrayList<>();
    public ContentCollectionRepository() {

    }
    public List<Content> findAll() {
        return contentList;
    }
    public Optional<Content> findById(Integer id) {
        return contentList.stream().filter(c -> c.id().equals(id)).findFirst();
    }

    public void save(Content content){
        contentList.add(content);
    }


    @PostConstruct
    private void init() {
        Content content = new Content(1, "My First Title", "My First Description", Status.IDEA, Type.ARTICLE, LocalDate.now(), null, "https://example.com");
        contentList.add(content);
    }
}
