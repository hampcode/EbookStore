package com.example.m3.dto;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
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
public class OrderDto {

    private Long id;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateCreate;
    private UserDto user;
    private List<OrderLineDto> lines;
    private Double total;
}