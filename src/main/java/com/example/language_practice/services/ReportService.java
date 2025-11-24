package com.example.language_practice.services;

import com.example.language_practice.dto.response.ApiResponse;
import com.example.language_practice.models.Report;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ReportService {
    ResponseEntity<ApiResponse<List<Report>>> getReports();

    ResponseEntity<ApiResponse<Report>> createReport(Report report);

    ResponseEntity<ApiResponse<Report>> updateReport(Report report, Long reportId);

    ResponseEntity<ApiResponse<String>> deleteReport(Long reportId);
}
