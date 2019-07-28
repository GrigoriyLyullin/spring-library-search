package ru.otus.springlibrary.search.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.springlibrary.search.domain.Genre;

public interface GenreBlockingRepository extends MongoRepository<Genre, ObjectId> {

}
