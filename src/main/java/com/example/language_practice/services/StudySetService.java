package com.example.language_practice.services;

import com.example.language_practice.dto.response.ApiResponse;
import com.example.language_practice.models.Report;
import com.example.language_practice.models.StudySet;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StudySetService {
    ResponseEntity<ApiResponse<List<StudySet>>> getAllStudySetByUserId(Long userId);

    ResponseEntity<ApiResponse<StudySet>> createStudy(Long userId,StudySet studySet);

    ResponseEntity<ApiResponse<StudySet>> updateStudySet(Long userId,Long studySetId,StudySet studySet);

    ResponseEntity<ApiResponse<StudySet>> deleteStudy(Long userId, Long studySetId);
}
