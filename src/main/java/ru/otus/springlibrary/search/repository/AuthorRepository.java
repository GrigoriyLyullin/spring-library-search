package ru.otus.springlibrary.search.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import ru.otus.springlibrary.search.domain.Author;

public interface AuthorRepository extends ReactiveMongoRepository<Author, ObjectId> {

    Flux<Author> findTop5ByFullNameContainingIgnoreCase(String fullName);
}
