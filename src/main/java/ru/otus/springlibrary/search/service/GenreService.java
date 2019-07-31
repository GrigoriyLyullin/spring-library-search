package ru.otus.springlibrary.search.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import ru.otus.springlibrary.search.NotFoundException;
import ru.otus.springlibrary.search.domain.Genre;
import ru.otus.springlibrary.search.repository.GenreBlockingRepository;
import ru.otus.springlibrary.search.repository.GenreRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class GenreService {

    private GenreBlockingRepository genreBlockingRepository;

    private GenreRepository genreRepository;

    @HystrixCommand(fallbackMethod = "getDefaultGenres", commandKey = "findAll")
    public List<Genre> findAll() {
        List<Genre> genres = genreBlockingRepository.findAll();
        System.out.println("genres.isEmpty(): " + genres.isEmpty());
        if (genres.isEmpty()) {
            throw new NotFoundException();
        }
        return genres;
    }

    public Flux<Genre> findTop5ByGenreContainingIgnoreCase(String genre) {
        return genreRepository.findTop5ByGenreContainingIgnoreCase(genre);
    }

    @HystrixCommand
    public List<Genre> getDefaultGenres() {
        return List.of(new Genre(ObjectId.get(), "Realistic Fiction"),
                new Genre(ObjectId.get(), "Romance"),
                new Genre(ObjectId.get(), "Satire"));
    }
}
