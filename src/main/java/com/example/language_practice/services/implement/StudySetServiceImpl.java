package com.example.language_practice.services.implement;

import com.example.language_practice.dto.response.ApiResponse;
import com.example.language_practice.exception.ErrorCode;
import com.example.language_practice.models.StudySet;
import com.example.language_practice.models.User;
import com.example.language_practice.repositories.StudySetRepository;
import com.example.language_practice.repositories.UserRepository;
import com.example.language_practice.services.StudySetService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class StudySetServiceImpl implements StudySetService {
    StudySetRepository studySetRepository;
    UserRepository userRepository;

    @Override
    public ResponseEntity<ApiResponse<List<StudySet>>> getAllStudySetByUserId(Long userId) {
        List<StudySet> list = studySetRepository.findByUser_UserId(userId);
        if(list.isEmpty()){
            return ResponseEntity.ok()
                    .body(
                            ApiResponse.<List<StudySet>>builder()
                                    .message("List study set is empty.")
                                    .build()
                    );
        }
        return ResponseEntity.ok()
                .body(
                        ApiResponse.<List<StudySet>>builder()
                                .result(list)
                                .build()
                );
    }

    @Override
    public ResponseEntity<ApiResponse<StudySet>> createStudy(Long userId, StudySet studySet) {
        if(!userRepository.existsById(userId)){
            return ResponseEntity.badRequest()
                    .body(
                            ApiResponse.<StudySet>builder()
                                    .message(ErrorCode.USER_NOT_EXISTED.getMessage())
                                    .build()
                    );
        }
        User user = userRepository.findById(userId).orElse(null);

        StudySet s = StudySet.builder()
                //setTitle là tên field
                .setTitle(studySet.getSetTitle())
                .subjects(studySet.getSubjects())
                .access(studySet.getAccess())
                .description(studySet.getDescription())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .rate(0)
                .user(user)
                .build();

        StudySet saved_set = studySetRepository.save(s);
        return ResponseEntity.badRequest()
                .body(
                        ApiResponse.<StudySet>builder()
                                .message("Created successfully!")
                                .result(saved_set)
                                .build()
                );
    }

    @Override
    public ResponseEntity<ApiResponse<StudySet>> updateStudySet(Long userId,Long studySetId, StudySet studySet) {
        if(!studySetRepository.existsById(studySetId)){
            return ResponseEntity.badRequest()
                    .body(
                            ApiResponse.<StudySet>builder()
                                    .message(ErrorCode.STUDY_SET_NOT_EXISTED.getMessage())
                                    .build()
                    );
        }

        if(!studySetRepository.existsBySetIdAndUser_UserId(studySetId,userId)){
            return ResponseEntity.badRequest()
                    .body(
                            ApiResponse.<StudySet>builder()
                                    .message("User có id "+userId+" không sở hữu set có id "+studySetId)
                                    .build()
                    );
        }

        StudySet s = studySetRepository.findById(studySetId).orElse(null);

        s.setSetTitle(studySet.getSetTitle());
        s.setSubjects(studySet.getSubjects());
        s.setAccess(studySet.getAccess());
        s.setDescription(studySet.getDescription());
        s.setUpdatedAt(LocalDateTime.now());

        StudySet set_saved = studySetRepository.save(s);

        return ResponseEntity.ok()
                .body(
                        ApiResponse.<StudySet>builder()
                                .message("Updated study set successfully")
                                .result(set_saved)
                                .build()
                );
    }

    @Override
    public ResponseEntity<ApiResponse<StudySet>> deleteStudy(Long userId, Long studySetId) {
        if(!studySetRepository.existsById(studySetId)){
            return ResponseEntity.badRequest()
                    .body(
                            ApiResponse.<StudySet>builder()
                                    .message(ErrorCode.STUDY_SET_NOT_EXISTED.getMessage())
                                    .build()
                    );
        }
        if(!studySetRepository.existsBySetIdAndUser_UserId(studySetId,userId)){
            return ResponseEntity.badRequest()
                    .body(
                            ApiResponse.<StudySet>builder()
                                    .message("User có id "+userId+" không sở hữu set có id "+studySetId)
                                    .build()
                    );
        }
        studySetRepository.deleteById(studySetId);

        return ResponseEntity.ok()
                .body(
                        ApiResponse.<StudySet>builder()
                                .message("Deleted study set successfully")
                                .build()
                );
    }
}
