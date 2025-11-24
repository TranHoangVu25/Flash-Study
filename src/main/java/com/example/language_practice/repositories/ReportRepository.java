package com.example.language_practice.repositories;

import com.example.language_practice.models.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReportRepository extends JpaRepository<Report,Long> {
    List<Report> findByUser_UserId(Long id);
}

