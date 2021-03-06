package com.wecode.bookstore.integration;

import com.wecode.bookstore.BookstoreApplication;
import com.wecode.bookstore.dto.BookDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = BookstoreApplication.class,
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class BookControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    @Sql(scripts = {"classpath:InsertInitialBookForTest.sql"})
    void shouldReturnBooksWhenApiCalled() {
        BookDto[] listOfBooks = testRestTemplate.getForObject("http://localhost:" + port + "/api/v1/books", BookDto[].class);
        assertThat(listOfBooks).isNotNull();
        assertThat(listOfBooks.length).isEqualTo(17);
    }

    @Test
    @Sql(scripts = {"classpath:InsertInitialBookForTest.sql"})
    void shouldReturnOneBookWhenCalledWithTestTitle() {
        BookDto[] bookDtos = testRestTemplate.getForObject("http://localhost:" + port + "/api/v1/books/MongoDB in Action", BookDto[].class);

        assertThat(bookDtos).isNotNull();
        assertThat(bookDtos.length).isEqualTo(1);
    }
}
