package com.example.language_practice.controllers;

import com.example.language_practice.dto.request.AuthenticationRequest;
import com.example.language_practice.dto.request.UserCreationRequest;
import com.example.language_practice.dto.request.UserUpdateRequest;
import com.example.language_practice.dto.response.ApiResponse;
import com.example.language_practice.models.User;
import com.example.language_practice.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class UserController {
    UserService userService;

    @Operation(
            summary = "Get all information about user",
            description = "Authenticate user using credentials and issue access token and refresh token. "
                    + "Tokens will be returned via HTTP-only cookies. "
                    + "The response body contains authentication information of the logged-in user.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    description = "Get all user",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AuthenticationRequest.class)
                    )
            ),
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "200",
                            description = "Login successful",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ApiResponse.class)
                            ),
                            headers = {
                                    @Header(
                                            name = HttpHeaders.SET_COOKIE,
                                            description = "JWT access token cookie and refresh token cookie"
                                    )
                            }
                    ),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized - invalid username or password",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ApiResponse.class)
                            )
                    ),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "400",
                            description = "Bad request - validation failed"
                    ),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "500",
                            description = "Internal server error"
                    )
            }
    )
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
