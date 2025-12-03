package com.example.language_practice.services;

import com.example.language_practice.dto.response.ApiResponse;
import com.example.language_practice.models.Achievement;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AchievementService {
    ResponseEntity<ApiResponse<List<Achievement>>> getAllAchievement();

    ResponseEntity<ApiResponse<Achievement>> addAchievement(Achievement achievement);
    ResponseEntity<ApiResponse<Achievement>> updateAllAchievement(Long id, Achievement achievement);

    ResponseEntity<ApiResponse<String>> deleteAchievement(Long id);

}
