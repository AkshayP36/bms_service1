package com.lld.BookMyShow.controllers;

import com.lld.BookMyShow.dtos.SignUpRequestDto;
import com.lld.BookMyShow.dtos.SignUpResponseDto;
import com.lld.BookMyShow.models.User;
import com.lld.BookMyShow.services.UserService;
import org.springframework.boot.micrometer.observation.autoconfigure.ObservationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<SignUpResponseDto> signUp(@RequestBody SignUpRequestDto signUpRequestDto){
        User user = userService.signup(signUpRequestDto.getName(),
                signUpRequestDto.getEmail(), signUpRequestDto.getPassword());

        SignUpResponseDto signUpResponseDto = new SignUpResponseDto(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(signUpResponseDto);

    }
}
