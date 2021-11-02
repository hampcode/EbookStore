package com.example.m3.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequestDto {
    private String username;
    private String password;
}