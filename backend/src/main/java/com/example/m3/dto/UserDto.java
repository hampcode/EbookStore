package com.example.m3.dto;

import lombok.*;

@Getter
@Setter

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String username;
}