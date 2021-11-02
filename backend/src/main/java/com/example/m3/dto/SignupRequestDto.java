package com.example.m3.dto;


import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignupRequestDto {
    private String username;
    private String password;
}