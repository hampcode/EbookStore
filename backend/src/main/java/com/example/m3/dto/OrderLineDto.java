package com.example.m3.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderLineDto {
    private Long id;
    private BookDto book;
    private Double price;
    private Double quantity;
    private Double total;
}