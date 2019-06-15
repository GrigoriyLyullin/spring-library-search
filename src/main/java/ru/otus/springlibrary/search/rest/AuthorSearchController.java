package ru.otus.springlibrary.search.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import ru.otus.springlibrary.search.dto.AuthorDTO;
import ru.otus.springlibrary.search.repository.AuthorRepository;

@RestController
public class AuthorSearchController {

    private AuthorRepository repository;

    public AuthorSearchController(AuthorRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/author")
    public Flux<AuthorDTO> all() {
        return repository.findAll().map(a -> new AuthorDTO(a.getId().toString(), a.getFullName()));
    }

    @GetMapping("/author/searchByName")
    public Flux<AuthorDTO> byName(@RequestParam("name") String name) {
        return repository.findTop5ByFullNameContainingIgnoreCase(name)
                .map(a -> new AuthorDTO(a.getId().toString(), a.getFullName()));
    }
}
