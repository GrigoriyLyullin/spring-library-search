package ru.otus.springlibrary.search.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import reactor.core.publisher.Flux;
import ru.otus.springlibrary.search.domain.Genre;

@CrossOrigin(origins = "*")
public interface GenreRepository extends ReactiveMongoRepository<Genre, ObjectId> {

    Flux<Genre> findTop5ByGenreContainingIgnoreCase(String genre);
}
