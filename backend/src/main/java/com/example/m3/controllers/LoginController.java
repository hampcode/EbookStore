package com.example.m3.controllers;

import com.example.m3.converters.UserConverter;
import com.example.m3.dto.LoginRequestDto;
import com.example.m3.dto.LoginResponseDto;
import com.example.m3.dto.SignupRequestDto;
import com.example.m3.dto.UserDto;
import com.example.m3.entities.User;
import com.example.m3.service.UserService;
import com.example.m3.utils.WrapperResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    private final UserService userService;

    private final UserConverter userConverter;

    public LoginController(UserService userService, UserConverter userConverter) {
        this.userService = userService;
        this.userConverter = userConverter;
    }


    @PostMapping("/signup")
    public ResponseEntity<WrapperResponse<UserDto>> signup(@RequestBody SignupRequestDto request){
        User user=userService.createUser(userConverter.signup(request));
        return new WrapperResponse<>(true,"success",userConverter.fromEntity(user))
                .createResponse();
    }

    @PostMapping("/login")
    public ResponseEntity<WrapperResponse<LoginResponseDto>> login(@RequestBody LoginRequestDto request){
        LoginResponseDto response=userService.login(request);
        return new WrapperResponse<>(true,"success",response)
                .createResponse();
    }

}