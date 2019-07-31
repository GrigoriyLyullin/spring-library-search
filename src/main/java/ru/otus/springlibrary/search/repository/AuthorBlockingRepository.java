package ru.otus.springlibrary.search.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.springlibrary.search.domain.Author;

public interface AuthorBlockingRepository extends MongoRepository<Author, ObjectId> {

}
