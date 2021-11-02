package com.example.m3.validators;


import com.example.m3.entities.User;
import com.example.m3.exception.IncorrectResourceRequestException;

public class UserValidator {
    public static void validate(User user){
        if(user.getUsername()==null || user.getUsername().trim().isEmpty()){
            throw new IncorrectResourceRequestException("El nombre de usuario es requerido");
        }

        if(user.getUsername().length()>30){
            throw new IncorrectResourceRequestException("El nombre de usuario es muy largo (max 30)");
        }

        if(user.getPassword()==null || user.getPassword().isEmpty()){
            throw new IncorrectResourceRequestException("El password es requerido");
        }
        if(user.getPassword().length()>30){
            throw new IncorrectResourceRequestException("El password de usuario es muy largo (max 30)");
        }
    }
}