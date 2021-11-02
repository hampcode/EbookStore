package com.example.m3.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponseDto {
    private UserDto user;
    private String token;
}