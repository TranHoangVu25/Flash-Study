package com.example.language_practice.services;

import com.example.language_practice.dto.request.UserCreationRequest;
import com.example.language_practice.dto.request.UserUpdateRequest;
import com.example.language_practice.dto.response.ApiResponse;
import com.example.language_practice.models.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    ResponseEntity<ApiResponse<List<User>>> getUsers();

    ResponseEntity<ApiResponse<User>> createUser(UserCreationRequest request);

    ResponseEntity<ApiResponse<User>> updateUser(UserUpdateRequest request,Long userId);

    ResponseEntity<ApiResponse<String>> deleteUser(Long userId);
}
