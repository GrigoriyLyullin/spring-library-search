package ru.otus.springlibrary.search.rest;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import ru.otus.springlibrary.search.domain.Author;
import ru.otus.springlibrary.search.dto.AuthorDTO;
import ru.otus.springlibrary.search.service.AuthorService;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
@AllArgsConstructor
public class AuthorSearchController {

    private AuthorService authorService;

    @GetMapping("/authors")
    public List<AuthorDTO> all() {
        System.out.println("AuthorSearchController.all():");
        List<Author> authors = authorService.findAll();
        System.out.println("authors: " + authors);
        return authors
                .stream()
                .map(a -> new AuthorDTO(a.getId().toString(), a.getFullName()))
                .collect(Collectors.toList());
    }

    @GetMapping("/authors/searchByName")
    public Flux<AuthorDTO> byName(@RequestParam("name") String name) {
        return authorService.findTop5ByFullNameContainingIgnoreCase(name)
                .map(a -> new AuthorDTO(a.getId().toString(), a.getFullName()));
    }
}
