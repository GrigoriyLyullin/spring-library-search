package ru.otus.springlibrary.search.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import ru.otus.springlibrary.search.NotFoundException;
import ru.otus.springlibrary.search.domain.Author;
import ru.otus.springlibrary.search.repository.AuthorBlockingRepository;
import ru.otus.springlibrary.search.repository.AuthorRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class AuthorService {

    private AuthorBlockingRepository authorBlockingRepository;

    private AuthorRepository authorRepository;

    @HystrixCommand(fallbackMethod = "getDefaultAuthors")
    public List<Author> findAll() {
        List<Author> authors = authorBlockingRepository.findAll();
        System.out.println("authors.isEmpty(): " + authors.isEmpty());
        if (authors.isEmpty()) {
            throw new NotFoundException();
        }
        return authors;
    }

    public Flux<Author> findTop5ByFullNameContainingIgnoreCase(String name) {
        return authorRepository.findTop5ByFullNameContainingIgnoreCase(name);
    }

    @HystrixCommand
    public List<Author> getDefaultAuthors() {
        List<Author> authors = new ArrayList<>();
        authors.add(new Author(ObjectId.get(), "Stephen King"));
        authors.add(new Author(ObjectId.get(), "Lewis Carroll"));
        authors.add(new Author(ObjectId.get(), "Joanne  Rowling"));
        System.out.println("getDefaultAuthors: " + authors);
        return authors;
    }
}
