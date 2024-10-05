package dev.alex.content_calendar.controller;

import dev.alex.content_calendar.model.Content;
import dev.alex.content_calendar.repository.ContentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/content")
public class ContentController {

//    private final ContentCollectionRepository repository;

    private final ContentRepository repository;

    public ContentController(ContentRepository repository) {
        this.repository = repository;
    }

    //GET ALL CONTENTS
    @GetMapping("")
    public List<Content> getAll() {
        return repository.findAll();
    }

    // GET 1 CONTENT
    @GetMapping("/{id}")
    public Optional<Content> findById(@PathVariable Integer id) {
        Optional<Content> content = repository.findById(id);
        if (content.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Content Not Found");
        return content;
    }

    // CREATE CONTENT
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public Content create(@RequestBody Content postContent) {
        Content content = new Content(null, postContent.title(), postContent.description(), postContent.status(), postContent.ContentType(), LocalDate.now(), null, postContent.url());
        return repository.save(content);
    }

    //UPDATE CONTENT
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping("/{id}")
    public Content update(@RequestBody Content putContent, @PathVariable Integer id) {
        Optional<Content> storedContent = repository.findById(id);
        if (storedContent.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Content id:" + id + "not found.");
        return repository.save(new Content(id, putContent.title(), putContent.description(), putContent.status(), putContent.ContentType(), storedContent.map(Content::dateCreated).orElse(null), LocalDate.now(), putContent.url()));
    }

    //DELETE CONTENT
    @ResponseStatus(HttpStatus.ACCEPTED)
    @DeleteMapping("/{id}")
    public Content delete(@PathVariable Integer id) {
        if (!repository.existsById(id))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Content id:" + id + "not found.");
        Optional<Content> content = repository.findById(id);
        repository.deleteById(id);
        return content.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Content id:" + id + "not found."));

    }

    //SEARCH CONTENT
    @GetMapping("/search/{keyword}")
    public List<Content> findByTitleContains(@PathVariable String keyword) {
        return repository.findAllByTitleContainsIgnoreCaseOrDescriptionContainsIgnoreCase(keyword, keyword);
    }
}
