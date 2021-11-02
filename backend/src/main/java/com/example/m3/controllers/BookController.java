package com.example.m3.controllers;

import com.example.m3.converters.BookConverter;
import com.example.m3.dto.BookDto;
import com.example.m3.entities.Book;
import com.example.m3.service.BookService;
import com.example.m3.utils.WrapperResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    private final BookConverter bookConverter;

    public BookController(BookService bookService,BookConverter bookConverter){
        this.bookService=bookService;
        this.bookConverter=bookConverter;
    }

    @GetMapping("/pageable")
    public ResponseEntity<Page<Book>> index(Pageable pageable) {
        Page<Book> books = bookService.index(pageable);
        return new ResponseEntity<Page<Book>>(books, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<WrapperResponse<List<BookDto>>> getAll() {
        List<Book> books = bookService.getAll();
        List<BookDto> bookDtos=bookConverter.fromEntity(books);
        return new WrapperResponse<>(true, "success", bookDtos)
                .createResponse(HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<WrapperResponse<BookDto>> get(@PathVariable Long id){
        Book book=bookService.getBook(id);
        BookDto bookDto=bookConverter.fromEntity(book);
        return new WrapperResponse<>(true, "success", bookDto)
                .createResponse(HttpStatus.OK);
    }



    @PostMapping
    public ResponseEntity<WrapperResponse<BookDto>> createBook(@RequestBody BookDto book) {
        Book bookNew= bookService.createBook(bookConverter.fromDTO(book));
        BookDto bookDto=bookConverter.fromEntity(bookNew);
        return new WrapperResponse<BookDto>(true, "success", bookDto)
                .createResponse(HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<WrapperResponse<BookDto>> updateBook(@RequestBody BookDto book){
        Book bookUpdate= bookService.updateBook(bookConverter.fromDTO(book));
        BookDto bookDto=bookConverter.fromEntity(bookUpdate);

        return new WrapperResponse<BookDto>(true, "success", bookDto)
                .createResponse(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<WrapperResponse<Void>> deleteBook(@PathVariable Long id){
        bookService.deleteBook(id);
        return new WrapperResponse<Void>(true, "success", null)
                .createResponse(HttpStatus.NO_CONTENT);
    }
}
