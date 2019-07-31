package ru.otus.springlibrary.search.rest;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import ru.otus.springlibrary.search.dto.GenreDTO;
import ru.otus.springlibrary.search.service.GenreService;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
@AllArgsConstructor
public class GenreSearchController {

    private GenreService genreService;

    @GetMapping("/genres")
    public List<GenreDTO> all() {
        return genreService.findAll()
                .stream()
                .map(g -> new GenreDTO(g.getId().toString(), g.getGenre()))
                .collect(Collectors.toList());
    }

    @GetMapping("/genres/searchByGenre")
    public Flux<GenreDTO> byName(@RequestParam("genre") String genre) {
        return genreService.findTop5ByGenreContainingIgnoreCase(genre)
                .map(g -> new GenreDTO(g.getId().toString(), g.getGenre()));
    }
}
