package dev.alex.content_calendar.controller;

import dev.alex.content_calendar.model.Content;
import dev.alex.content_calendar.repository.ContentCollectionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

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
    public Content create (@RequestBody Content content){
        repository.save(content);
        return content;
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping("/{id}")
    public Content update(@RequestBody Content content, @PathVariable Integer id) {
        if (!repository.existsById(id))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Content id:" + id + "not found.");
        repository.update(content);
        return content;
        }

        @ResponseStatus(HttpStatus.ACCEPTED)
        @DeleteMapping("/{id}")
        public Content delete(@PathVariable Integer id) {
            if (!repository.existsById(id))
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Content id:" + id + "not found.");
            Optional<Content> content = repository.findById(id);
            repository.deleteById(id);
            return content.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Content id:" + id + "not found."));

        }
    }
