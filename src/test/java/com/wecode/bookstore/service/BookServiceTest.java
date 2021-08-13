package com.wecode.bookstore.service;

import com.wecode.bookstore.dto.BookDto;
import com.wecode.bookstore.model.Book;
import com.wecode.bookstore.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @InjectMocks
    private BookService bookService;

    @Mock // get proxy instance, not real one
    private BookRepository bookRepository;

    @Mock
    private ModelMapper mapper;

    @Test
    void shouldReturnListOfBookDTO() {
        List<Book> books = new ArrayList<>();
        Book book = getBook();
        books.add(book);
        BookDto bookDto = getBookDto();
        when(bookRepository.findAll()).thenReturn(books);
        when(mapper.map(book, BookDto.class)).thenReturn(bookDto);

        List<BookDto> bookDtos = bookService.getBooks();
        assertThat(1).isEqualTo(bookDtos.size());
        assertThat(bookDtos.get(0))
                .isNotNull()
                .hasFieldOrPropertyWithValue("title", "test title")
                .hasFieldOrPropertyWithValue("description", "test description")
                .hasFieldOrPropertyWithValue("releaseYear", 2021);
    }

    private Book getBook() {
        return Book.builder()
                .title("test title")
                .description("test description")
                .id(UUID.randomUUID())
                .releaseYear(2021)
                .build();
    }

    private BookDto getBookDto() {
        return BookDto.builder()
                .title("test title")
                .description("test description")
                .id(UUID.randomUUID())
                .releaseYear(2021)
                .build();
    }
}
