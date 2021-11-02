package com.example.m3.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class BookDto {
    private Long id;
    private String title;
    private String description;
    private Double price;
}