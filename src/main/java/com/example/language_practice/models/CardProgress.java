package com.example.language_practice.models;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "card_progress")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CardProgress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "card_progress_id")
    private Long cardProgressId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "card_id")
    private Card card;

    @Column(name = "study_count")
    private Integer studyCount;

    @Column(name = "correct_count")
    private Integer correctCount;

    @Column(name = "last_review_at")
    private LocalDateTime lastReviewAt;
}
