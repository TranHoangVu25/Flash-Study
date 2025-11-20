package com.example.language_practice.controllers;

import com.example.language_practice.dto.request.UserCreationRequest;
import com.example.language_practice.dto.request.UserUpdateRequest;
import com.example.language_practice.dto.response.ApiResponse;
import com.example.language_practice.models.User;
import com.example.language_practice.services.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class UserController {
    UserService userService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<User>>> getAllUsers() {
        return userService.getUsers();
    }

    @PostMapping
    public ResponseEntity<ApiResponse<User>> createUser(
            @RequestBody UserCreationRequest request
    ) {
        return userService.createUser(request);
    }

    @PutMapping("{userId}")
    public ResponseEntity<ApiResponse<User>> updateUser(
            @RequestBody UserUpdateRequest request,
            @PathVariable Long userId
    ){
        return userService.updateUser(request,userId);
    }

    @DeleteMapping("{userId}")
    public ResponseEntity<ApiResponse<String>> deleteUser(
            @PathVariable Long userId
    ){
        return userService.deleteUser(userId);
    }
}
