package com.example.language_practice.controllers;

import com.example.language_practice.dto.response.ApiResponse;
import com.example.language_practice.models.Achievement;
import com.example.language_practice.repositories.AchievementRepository;
import com.example.language_practice.services.AchievementService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/v1/achievement")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AchievementController {
    AchievementService  achievementService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Achievement>>> getAllAchievement(){
        return achievementService.getAllAchievement();
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Achievement>> addAchievement(
            @RequestBody Achievement achievement){
        return  achievementService.addAchievement(achievement);
    }

    @PutMapping("/{achievementId}")
    public  ResponseEntity<ApiResponse<Achievement>> updateAchievement(
            @RequestBody Achievement achievement,
            @PathVariable Long achievementId
    ){
        return achievementService.updateAllAchievement(achievementId,achievement);
    }

    @DeleteMapping("/{achievementId}")
    public ResponseEntity<ApiResponse<String>> deleteAchievement(
            @PathVariable Long achievementId
    ){
        return achievementService.deleteAchievement(achievementId);
    }
}
