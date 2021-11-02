package com.example.m3.service;

import com.example.m3.entities.Book;
import com.example.m3.exception.GeneralServiceException;
import com.example.m3.exception.IncorrectResourceRequestException;
import com.example.m3.exception.ResourceNotFoundException;
import com.example.m3.repositories.BookRepository;
import com.example.m3.validators.BookValidator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    public Page<Book> index(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    public List<Book> getAll(){
        return bookRepository.findAll();
    }


    public List<Book> getAllTops6Books(){
        return bookRepository.findTop6ByOrderByTitleAsc();
    }

    public Book getBook(Long id){
        Optional<Book> book = bookRepository.findById(id);
        return book.orElseThrow(() -> new ResourceNotFoundException("Book not found"));
    }

    @Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED)
    public Book createBook(Book book){
        try {
            BookValidator.validate(book);
            return bookRepository.save(book);
        } catch (IncorrectResourceRequestException | ResourceNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new GeneralServiceException(e.getMessage(), e);
        }

    }

    @Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED)
    public Book updateBook(Book book){
        BookValidator.validate(book);

        Book bookFromDb=bookRepository.findById(book.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No existe el producto"));

        bookFromDb.setTitle(book.getTitle());
        bookFromDb.setDescription(book.getDescription());
        bookFromDb.setPrice(book.getPrice());

        return bookRepository.save(bookFromDb);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED)
    public void deleteBook(Long id){
        Book bookFromDb=this.getBook(id);
        bookRepository.delete(bookFromDb);
    }

}
