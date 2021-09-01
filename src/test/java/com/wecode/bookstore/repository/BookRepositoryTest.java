package com.wecode.bookstore.repository;

import com.wecode.bookstore.model.Book;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.stream.StreamSupport;

@ExtendWith(SpringExtension.class)
@DataJpaTest // by default tests annotated with this are transactional by default, creates the first instance for every test case
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    @Sql(scripts = {"classpath:InsertInitialBookForTest.sql"})
    void shouldFetchAllBooksInDB() {
        Iterable<Book> all = bookRepository.findAll();
        long totalBookCount = StreamSupport.stream(all.spliterator(), false).count();
        Assertions.assertEquals(totalBookCount, 17);
    }

    @Test
    @Sql(scripts = {"classpath:InsertInitialBookForTest.sql"})
    void shouldReturnOneBookWhenTitle() {
        List<Book> books = bookRepository.findBooksByTitle("Java Edition 1");
        Assertions.assertEquals(books.size(), 1);
    }

    @Test
    @Sql(scripts = {"classpath:InsertInitialBookForTest.sql"})
    void shouldReturnOneBookWhenTitleIgnoreCase() {
        List<Book> books = bookRepository.findBooksByTitleIgnoreCase("java edition 1");
        Assertions.assertEquals(books.size(), 1);
    }
}
