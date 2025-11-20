package com.example.language_practice.models;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "achievement_user")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AchievementUser {

    // Có thể dùng Composite Key (@EmbeddedId), nhưng ở đây dùng ID tự tăng cho đơn giản nếu cần
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "achievement_id")
    private Achievement achievement;

    @Column(name = "achievement_at")
    private LocalDateTime achievementAt;
}
