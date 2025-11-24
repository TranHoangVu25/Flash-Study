package com.example.language_practice.services.implement;

import com.example.language_practice.dto.response.ApiResponse;
import com.example.language_practice.exception.ErrorCode;
import com.example.language_practice.models.Report;
import com.example.language_practice.models.User;
import com.example.language_practice.repositories.ReportRepository;
import com.example.language_practice.repositories.UserRepository;
import com.example.language_practice.services.ReportService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@Slf4j
public class ReportServiceImpl implements ReportService {
    ReportRepository reportRepository;
    UserRepository userRepository;
    @Override
    public ResponseEntity<ApiResponse<List<Report>>> getReports() {
        List<Report> reports = reportRepository.findAll();
        if(reports.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        else return ResponseEntity.ok()
                .body(
                        ApiResponse.<List<Report>>builder()
                                .result(reports)
                                .build()
                );
    }

    @Override
    public ResponseEntity<ApiResponse<List<Report>>> getReportByUserId(Long userId) {
        if(!userRepository.existsById(userId)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(
                            ApiResponse.<List<Report>>builder()
                                    .message(ErrorCode.USER_NOT_EXISTED.getMessage())
                                    .build()
                    );
        }
        List<Report> reports = reportRepository.findByUser_UserId(userId);
        if(reports.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        else return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(
                        ApiResponse.<List<Report>>builder()
                                .result(reports)
                                .build()
                );
    }

    @Override
    public ResponseEntity<ApiResponse<Report>> createReport(Long userId,Report report) {
        if(!userRepository.existsById(userId)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(
                            ApiResponse.<Report>builder()
                                    .message(ErrorCode.USER_NOT_EXISTED.getMessage())
                                    .build()
                    );
        }
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new RuntimeException("In create report. User not found"));

        Report report1 = new Report().builder()
                .type(report.getType())
                .description(report.getDescription())
                .status(report.getStatus())
                .contactEmail(user.getEmail())
                .title(report.getTitle())
                .stepsToReproduce(report.getStepsToReproduce())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .user(user)
                .build();
        Report saved = reportRepository.save(report1);
        return ResponseEntity.ok()
                .body(
                        ApiResponse.<Report>builder()
                                .message("Created report successfully!")
                                .result(saved)
                                .build()
                );
    }

    @Override
    public ResponseEntity<ApiResponse<Report>> updateReport(Report r, Long reportId) {
        if(!reportRepository.existsById(reportId)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(
                            ApiResponse.<Report>builder()
                                    .message(ErrorCode.REPORT_NOT_EXISTED.getMessage())
                                    .build()
                    );
        }
        Report report = reportRepository.findById(reportId).get();

        report.setType(r.getType());
        report.setTitle(r.getTitle());
        report.setDescription(r.getDescription());
        report.setStatus(r.getStatus());
        report.setStepsToReproduce(r.getStepsToReproduce());
        report.setUpdatedAt(LocalDateTime.now());

        Report saved_report = reportRepository.save(report);
        return ResponseEntity.ok()
                .body(
                        ApiResponse.<Report>builder()
                                .message("Updated report successfully!")
                                .result(saved_report)
                                .build()
                );
    }

    @Override
    public ResponseEntity<ApiResponse<String>> deleteReport(Long reportId) {
        if(!reportRepository.existsById(reportId)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(
                            ApiResponse.<String>builder()
                                    .message(ErrorCode.REPORT_NOT_EXISTED.getMessage())
                                    .build()
                    );
        }
        reportRepository.deleteById(reportId);
        return ResponseEntity.ok()
                .body(
                        ApiResponse.<String>builder()
                                .message("Deleted report successfully!")
                                .build()
                );
    }
}
