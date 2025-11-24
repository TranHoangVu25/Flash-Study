package com.example.language_practice.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "report")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_id")
    private Long reportId;

    private String type; //Error System, Error UI

    private String description;

    private String status; //Resolved, In Process, Not Resolved

    @Column
    String contactEmail;

    @Column(nullable=false, length=255)
    String title;

    @Column(columnDefinition="TEXT",nullable=false)
    String stepsToReproduce;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable=false)
    LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore //thêm vào để tránh vòng lặp vô tận
    private User user;
}
