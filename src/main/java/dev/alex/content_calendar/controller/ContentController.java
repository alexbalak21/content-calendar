package dev.alex.content_calendar.controller;

import dev.alex.content_calendar.model.Content;
import dev.alex.content_calendar.repository.ContentCollectionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/content")
public class ContentController {

    private final ContentCollectionRepository repository;

    public ContentController(ContentCollectionRepository repository) {
        this.repository = repository;
    }

    @GetMapping("")
    public List<Content> findAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Content findById(@PathVariable Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Content id:" + id + "not found."));
    }
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public void create (@RequestBody Content content){
        repository.save(content);
    }

    public void update(@RequestBody Content content, Integer id) {

    }
}
