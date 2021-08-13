package com.wecode.bookstore.controller;

import com.wecode.bookstore.dto.BookDto;
import com.wecode.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public ResponseEntity<List<BookDto>> getBooks() {
        List<BookDto> books = bookService.getBooks();
        return ResponseEntity.ok(books);
    }
}
