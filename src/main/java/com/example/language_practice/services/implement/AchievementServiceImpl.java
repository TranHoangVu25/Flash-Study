package com.example.language_practice.services.implement;

import com.example.language_practice.dto.response.ApiResponse;
import com.example.language_practice.exception.ErrorCode;
import com.example.language_practice.models.Achievement;
import com.example.language_practice.models.Card;
import com.example.language_practice.models.StudySet;
import com.example.language_practice.repositories.AchievementRepository;
import com.example.language_practice.repositories.AchievementUserRepository;
import com.example.language_practice.services.AchievementService;
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
public class AchievementServiceImpl implements AchievementService {
    AchievementRepository achievementRepository;

    @Override
    public ResponseEntity<ApiResponse<List<Achievement>>> getAllAchievement() {
        List<Achievement> list = achievementRepository.findAll();
        if(list.isEmpty()){
            return ResponseEntity.ok()
                    .body(
                            ApiResponse.<List<Achievement>>builder()
                                    .message("List achievement set is empty.")
                                    .build()
                    );
        }
        return ResponseEntity.ok()
                .body(
                        ApiResponse.<List<Achievement>>builder()
                                .result(list)
                                .build()
                );
    }

    @Override
    public ResponseEntity<ApiResponse<Achievement>> addAchievement(Achievement achievement) {
        if (achievementRepository.existsByName(achievement.getName())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(
                            ApiResponse.<Achievement>builder()
                                    .message(ErrorCode.ACHIEVEMENT_EXISTED.getMessage())
                                    .build()
                    );
        }

        Achievement a = Achievement.builder()
                .name(achievement.getName())
                .description(achievement.getDescription())
                .criteria(achievement.getCriteria())
                .iconUrl(achievement.getIconUrl())
                .build();

        return ResponseEntity.ok()
                .body(
                        ApiResponse.<Achievement>builder()
                                .message("Create achievement successfully")
                                .result(achievementRepository.save(a))
                                .build()
                );
    }

    @Override
    public ResponseEntity<ApiResponse<Achievement>> updateAllAchievement(Long id, Achievement achievement) {
        if (!achievementRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(
                            ApiResponse.<Achievement>builder()
                                    .message(ErrorCode.ACHIEVEMENT_NOT_EXISTED.getMessage())
                                    .build()
                    );
        }
        Achievement a = achievementRepository.findById(id).get();

        a.setName(achievement.getName());
        a.setDescription(achievement.getDescription());
        a.setCriteria(achievement.getCriteria());
        a.setIconUrl(achievement.getIconUrl());

        return ResponseEntity.ok()
                .body(
                        ApiResponse.<Achievement>builder()
                                .message("Update achievement successfully")
                                .result(achievementRepository.save(a))
                                .build()
                );
    }

    @Override
    public ResponseEntity<ApiResponse<String>> deleteAchievement(Long id) {
        if (!achievementRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(
                            ApiResponse.<String>builder()
                                    .message(ErrorCode.ACHIEVEMENT_NOT_EXISTED.getMessage())
                                    .build()
                    );
        }

        achievementRepository.deleteById(id);

        return ResponseEntity.ok()
                .body(
                        ApiResponse.<String>builder()
                                .message("Delete achievement successfully")
                                .build()
                );
    }
}
