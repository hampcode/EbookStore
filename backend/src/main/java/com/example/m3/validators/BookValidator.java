package com.example.m3.validators;


import com.example.m3.entities.Book;
import com.example.m3.exception.IncorrectResourceRequestException;

public class BookValidator {

    public static void validate(Book book) {

        if(book.getTitle() == null || book.getTitle().trim().isEmpty()) {
            throw new IncorrectResourceRequestException("El titutlo es requerido");

        }

        if(book.getTitle().length() < 4) {
            throw new IncorrectResourceRequestException("El titulo debe ser mayor a 4 caracteres");

        }


        if(book.getDescription() == null || book.getDescription().trim().isEmpty()) {
            throw new IncorrectResourceRequestException("La descripción es requerido");

        }

        if(book.getDescription().length() < 10) {
            throw new IncorrectResourceRequestException("La descripción debe ser mayor a 10 caracteres");

        }

        if(book.getPrice() == null) {
            throw new IncorrectResourceRequestException("El precio es requerido");
        }

        if(book.getPrice() <= 0) {
            throw new IncorrectResourceRequestException("El precio es incorrecto");
        }
    }
}