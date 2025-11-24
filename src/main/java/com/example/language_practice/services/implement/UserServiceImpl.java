package com.example.language_practice.services.implement;

import com.example.language_practice.dto.request.UserCreationRequest;
import com.example.language_practice.dto.request.UserUpdateRequest;
import com.example.language_practice.dto.response.ApiResponse;
import com.example.language_practice.exception.ErrorCode;
import com.example.language_practice.models.User;
import com.example.language_practice.repositories.UserRepository;
import com.example.language_practice.services.UserService;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UserServiceImpl implements UserService {
    UserRepository userRepository;

    @Override
    public ResponseEntity<ApiResponse<List<User>>> getUsers() {
        List<User> users = userRepository.findAll();
        if (users.isEmpty()) {
            return ResponseEntity.ok().body(
                    ApiResponse.<List<User>>builder()
                            .message("No users found.")
                            .build()
            );
        }
        else
            return ResponseEntity.ok().body(
                ApiResponse.<List<User>>builder()
                        .result(users)
                        .build()
        );
    }

    @Override
    public ResponseEntity<ApiResponse<User>> createUser(UserCreationRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(
                            ApiResponse.<User>builder()
                                    .message(ErrorCode.USER_EXISTED.getMessage())
                                    .build()
                    );
        }
        User user = new User().builder()
                .email(request.getEmail())
                .password(request.getPassword())
                .fullName(request.getFullName())
                .role(request.getRole())
                .createdAt(request.getCreatedAt())
                .updatedAt(request.getUpdatedAt())
                .signInCount(request.getSignInCount())
                .build();
        User savedUser = userRepository.save(user);
        return ResponseEntity.ok()
                .body(
                        ApiResponse.<User>builder()
                                .message("Successfully created User")
                                .result(savedUser)
                                .build()
                );
    }

    @Override
    public ResponseEntity<ApiResponse<User>> updateUser(UserUpdateRequest request, Long userId) {
        if (!userRepository.existsById(userId)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(
                            ApiResponse.<User>builder()
                                    .message(ErrorCode.USER_NOT_EXISTED.getMessage())
                                    .build()
                    );
        }
        User user = userRepository.findById(userId).get();

        user.setFullName(request.getFullName());
        user.setPassword(request.getPassword());
        user.setUpdatedAt(LocalDateTime.now());

        User savedUser = userRepository.save(user);

        return ResponseEntity.ok()
                    .body(
                            ApiResponse.<User>builder()
                                    .message("Updated user successfully!")
                                    .result(savedUser)
                                    .build()
                    );
    }

    @Override
    public ResponseEntity<ApiResponse<String>> deleteUser(Long userId) {
        if (!userRepository.existsById(userId)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(
                            ApiResponse.<String>builder()
                                    .message(ErrorCode.USER_NOT_EXISTED.getMessage())
                                    .build()
                    );
        }

        userRepository.deleteById(userId);

        return ResponseEntity.ok()
                .body(
                        ApiResponse.<String>builder()
                                .message("Deleted user successfully!")
                                .build()
                );
    }

    @Override
    @Transactional
    public ResponseEntity<ApiResponse<String>> confirmUser(String token) {
        User user = userRepository.findByConfirmationToken(token)
                .orElse(null);

        if (user == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ApiResponse.<String>builder()
                            .code(ErrorCode.INVALID_TOKEN.getCode())
                            .message("Invalid or expired token")
                            .build());
        }

        user.setCreatedAt(LocalDateTime.now());
//        user.setConfirmationToken(token);
        user.setUpdatedAt(LocalDateTime.now());
        user.setConfirmationAt(LocalDateTime.now());

        userRepository.save(user);

        return ResponseEntity.ok(
                ApiResponse.<String>builder()
                        .message("Account confirmed successfully")
                        .build()
        );
    }
}
