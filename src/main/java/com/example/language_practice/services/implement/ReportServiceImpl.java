package com.example.language_practice.services.implement;

import com.example.language_practice.dto.response.ApiResponse;
import com.example.language_practice.models.Report;
import com.example.language_practice.repositories.ReportRepository;
import com.example.language_practice.services.ReportService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class ReportServiceImpl implements ReportService {
    ReportRepository reportRepository;

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
    public ResponseEntity<ApiResponse<Report>> createReport(Report report) {

        return null;
    }

    @Override
    public ResponseEntity<ApiResponse<Report>> updateReport(Report report, Long reportId) {
        return null;
    }

    @Override
    public ResponseEntity<ApiResponse<String>> deleteReport(Long reportId) {
        return null;
    }
}
