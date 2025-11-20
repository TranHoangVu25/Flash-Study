package com.example.language_practice.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Entity
@Table(name = "card")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "card_id")
    private Long cardId;

    @Column(name = "front_text", columnDefinition = "TEXT")
    private String frontText;

    @Column(name = "back_text", columnDefinition = "TEXT")
    private String backText;

    // Lưu ý: DB để time(7), Java dùng LocalTime
    @Column(name = "created_at")
    private LocalTime createdAt;

    private Integer numerical; // Thứ tự sắp xếp card

    @Column(name = "front_language")
    private String frontLanguage;

    @Column(name = "back_language")
    private String backLanguage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "set_id", nullable = false)
    private StudySet studySet;
}
