package com.example.language_practice.controllers;

import com.example.language_practice.dto.response.ApiResponse;
import com.example.language_practice.models.StudySet;
import com.example.language_practice.services.StudySetService;
import com.example.language_practice.utils.UserContextHolder;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/study-set")
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class StudySetController {
    StudySetService  studySetService;

    @GetMapping()
    public ResponseEntity<ApiResponse<List<StudySet>>> getStudyAllSets() {
        Long userId = UserContextHolder.getUserId();
        return studySetService.getAllStudySetByUserId(userId);
    }

    @PostMapping()
    public ResponseEntity<ApiResponse<StudySet>> createStudySet(@RequestBody StudySet studySet){
        Long userId = UserContextHolder.getUserId();
        log.info("User id:"+userId);
        return studySetService.createStudy(userId, studySet);
    }

    @PutMapping("/{studySetId}")
    public ResponseEntity<ApiResponse<StudySet>> updateStudySet(
            @RequestBody StudySet studySet,
        @PathVariable long studySetId
        ){
        Long userId = UserContextHolder.getUserId();
        return studySetService.updateStudySet(userId,studySetId ,studySet);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<StudySet>> deleteStudySet(
            @PathVariable Long id
    ){
        Long userId = UserContextHolder.getUserId();
        return studySetService.deleteStudy(userId,id);
    }
}
