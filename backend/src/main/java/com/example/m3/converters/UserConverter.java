package com.example.m3.converters;


import com.example.m3.dto.SignupRequestDto;
import com.example.m3.dto.UserDto;
import com.example.m3.entities.User;

public class UserConverter extends  AbstractConverter<User, UserDto> {

    @Override
    public UserDto fromEntity(User entity) {
        if(entity == null) return null;
        return UserDto.builder()
                .id(entity.getId())
                .username(entity.getUsername())
                .build();
    }

    @Override
    public User fromDTO(UserDto dto) {
        if(dto == null) return null;
        return User.builder()
                .id(dto.getId())
                .username(dto.getUsername())
                .build();
    }

    public User signup(SignupRequestDto dto){
        if(dto == null) return null;
        return User.builder()
                .username(dto.getUsername())
                .password(dto.getPassword())
                .build();
    }
}