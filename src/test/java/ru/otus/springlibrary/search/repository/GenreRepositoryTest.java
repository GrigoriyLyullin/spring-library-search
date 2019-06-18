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
import ru.otus.springlibrary.search.domain.Genre;

@RunWith(SpringRunner.class)
@DataMongoTest
public class GenreRepositoryTest {

    @Autowired
    private GenreRepository repository;

    @Before
    public void setUp() {
        Mono<Void> deleteAll = repository.deleteAll();
        StepVerifier.create(deleteAll).verifyComplete();

        Mono<Genre> testGenre = repository.save(new Genre("test genre"));
        StepVerifier.create(testGenre).expectNextCount(1L).verifyComplete();
    }

    @Test
    public void testFindExistingElement() {
        Flux<Genre> genres = repository.findTop5ByGenreContainingIgnoreCase("test genre");

        StepVerifier
                .create(genres)
                .expectNextMatches(genre -> "test genre".equals(genre.getGenre()))
                .expectComplete()
                .verify();
    }

    @Test
    public void testFindExistingElementByPartialMatch() {
        Flux<Genre> genres = repository.findTop5ByGenreContainingIgnoreCase("st ge");

        StepVerifier
                .create(genres)
                .expectNextMatches(genre -> "test genre".equals(genre.getGenre()))
                .expectComplete()
                .verify();
    }

    @Test
    public void testFindNotExistingElement() {
        Flux<Genre> genres = repository.findTop5ByGenreContainingIgnoreCase("non existing genre");

        StepVerifier
                .create(genres)
                .expectComplete()
                .verify();
    }
}
