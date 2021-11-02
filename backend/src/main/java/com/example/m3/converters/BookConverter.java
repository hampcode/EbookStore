package com.example.m3.converters;


import com.example.m3.dto.BookDto;
import com.example.m3.entities.Book;

public class BookConverter extends AbstractConverter<Book, BookDto>{

    @Override
    public BookDto fromEntity(Book entity) {
        if(entity == null) return null;

        return BookDto.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .description((entity.getDescription()))
                .price(entity.getPrice())
                .build();
    }

    @Override
    public Book fromDTO(BookDto dto) {
        if(dto == null) return null;
        return Book.builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .description((dto.getDescription()))
                .price(dto.getPrice())
                .build();
    }

}