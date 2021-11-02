package com.example.m3.controllers;

import com.example.m3.converters.BookConverter;
import com.example.m3.dto.BookDto;
import com.example.m3.entities.Book;
import com.example.m3.service.BookService;
import com.example.m3.utils.WrapperResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/books")
public class HomeController {

    private final BookService bookService;

    private final BookConverter bookConverter;

    public HomeController(BookService bookService,BookConverter bookConverter){
        this.bookService=bookService;
        this.bookConverter=bookConverter;
    }
    @GetMapping("/latest-books")
    public ResponseEntity<WrapperResponse<List<BookDto>>> getAll() {
        List<Book> books=bookService.getAllTops6Books();
        List<BookDto> bookDtos=bookConverter.fromEntity(books);
        return new WrapperResponse<>(true, "success", bookDtos)
                .createResponse(HttpStatus.OK);
    }
}
