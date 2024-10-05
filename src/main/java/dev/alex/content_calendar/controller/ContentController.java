package dev.alex.content_calendar.controller;

import dev.alex.content_calendar.model.Content;
import dev.alex.content_calendar.repository.ContentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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

    @GetMapping("")
    public List<Content> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Content> findById(@PathVariable Integer id) {
        Optional<Content> content = repository.findById(id);
        if (content.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Content Not Found");
        return content;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public void create(@RequestBody Content content) {
        repository.save(content);
    }
//
//    @ResponseStatus(HttpStatus.ACCEPTED)
//    @PutMapping("/{id}")
//    public Content update(@RequestBody Content content, @PathVariable Integer id) {
//        if (!repository.existsById(id))
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Content id:" + id + "not found.");
//        repository.update(content);
//        return content;
//    }
//
//    @ResponseStatus(HttpStatus.ACCEPTED)
//    @DeleteMapping("/{id}")
//    public Content delete(@PathVariable Integer id) {
//        if (!repository.existsById(id))
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Content id:" + id + "not found.");
//        Optional<Content> content = repository.findById(id);
//        repository.deleteById(id);
//        return content.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Content id:" + id + "not found."));
//
//    }
}
