package com.example.language_practice.controllers;

import com.example.language_practice.dto.response.ApiResponse;
import com.example.language_practice.models.Report;
import com.example.language_practice.repositories.ReportRepository;
import com.example.language_practice.services.ReportService;
import com.example.language_practice.utils.UserContextHolder;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/v1/report")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ReportController {
    ReportService reportService;

    @GetMapping("get-all")
    public ResponseEntity<ApiResponse<List<Report>>> getAllReports(){
        return reportService.getReports();
    }

    @GetMapping("get-by-userId")
    public ResponseEntity<ApiResponse<List<Report>>> getReportByUserId(
    ){
        Long userId = UserContextHolder.getUserId();
        return reportService.getReportByUserId(userId);
    }

    @PostMapping()
    public ResponseEntity<ApiResponse<Report>> createReport(
            @RequestBody Report report
    ){
        Long userId = UserContextHolder.getUserId();
        return reportService.createReport(userId, report);
    }

    @PutMapping("/{reportId}")
    public ResponseEntity<ApiResponse<Report>> updateReport(
            @RequestBody Report report,
            @PathVariable Long reportId
    ){
        return reportService.updateReport(report, reportId);
    }

    @DeleteMapping("/{reportId}")
    public ResponseEntity<ApiResponse<String>> deleteReport(
            @PathVariable Long reportId
    ){
        return reportService.deleteReport(reportId);
    }
}
