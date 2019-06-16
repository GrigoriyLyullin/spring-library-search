package ru.otus.springlibrary.search.repository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import ru.otus.springlibrary.search.domain.Author;

@RunWith(SpringRunner.class)
@DataMongoTest
public class AuthorRepositoryTest {

    @Autowired
    private AuthorRepository repository;

    @Before
    public void setUp() {
        Mono<Void> deleteAll = repository.deleteAll();
        StepVerifier.create(deleteAll).verifyComplete();

        Mono<Author> testAuthor = repository.save(new Author("test author"));
        StepVerifier.create(testAuthor).expectNextCount(1L).verifyComplete();
    }

    @Test
    public void testFindExistingElement() {
        Flux<Author> authors = repository.findTop5ByFullNameContainingIgnoreCase("test author");

        StepVerifier
                .create(authors)
                .expectNextMatches(author -> "test author".equals(author.getFullName()))
                .expectComplete()
                .verify();
    }

    @Test
    public void testFindExistingElementByPartialMatch() {
        Flux<Author> authors = repository.findTop5ByFullNameContainingIgnoreCase("t auth");

        StepVerifier
                .create(authors)
                .expectNextMatches(author -> "test author".equals(author.getFullName()))
                .expectComplete()
                .verify();
    }

    @Test
    public void testFindNotExistingElement() {
        Flux<Author> authors = repository.findTop5ByFullNameContainingIgnoreCase("non existing author");

        StepVerifier
                .create(authors)
                .expectComplete()
                .verify();
    }
}