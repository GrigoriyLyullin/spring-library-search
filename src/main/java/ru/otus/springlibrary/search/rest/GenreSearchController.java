package ru.otus.springlibrary.search.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import ru.otus.springlibrary.search.dto.GenreDTO;
import ru.otus.springlibrary.search.repository.GenreRepository;

@RestController
public class GenreSearchController {

    private GenreRepository repository;

    public GenreSearchController(GenreRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/genres")
    public Flux<GenreDTO> all() {
        return repository.findAll().map(g -> new GenreDTO(g.getId().toString(), g.getGenre()));
    }

    @GetMapping("/genres/searchByGenre")
    public Flux<GenreDTO> byName(@RequestParam("genre") String genre) {
        return repository.findTop5ByGenreContainingIgnoreCase(genre).map(g -> new GenreDTO(g.getId().toString(), g.getGenre()));
    }
}
