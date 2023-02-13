package com.com4in.jwt_tutorial.controller;

import com.com4in.jwt_tutorial.dto.UserDto;
import com.com4in.jwt_tutorial.entity.Users;
import com.com4in.jwt_tutorial.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("/api")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<Users> signup(@Valid @RequestBody UserDto userDto) {
        System.out.println("testsetestsetsetsetsetsetsetsetsetse");
        return ResponseEntity.ok(userService.signup(userDto));
    }

    @GetMapping("/user")
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    public ResponseEntity<Users> getMyUserInfo(HttpServletRequest request) {
        return ResponseEntity.ok(userService.getMyUserWithAuthorities().get());
    }

    @GetMapping("/user/{username}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<Users> getUserInfo(@PathVariable String username) {
        System.out.println("getUserInfo====getUserInfo====getUserInfo====getUserInfo===getUserInfo==getUserInfo==getUserInfo==getUserInfo==getUserInfo==getUserInfo==getUserInfo");
        return ResponseEntity.ok(userService.getUserWithAuthorities(username).get());
    }
}