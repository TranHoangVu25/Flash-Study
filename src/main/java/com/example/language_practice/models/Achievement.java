package com.example.language_practice.models;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "achievement")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Achievement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "achievement_id")
    private Long achievementId;

    private String name;
    private String description;
    private String criteria;

    @Column(name = "icon_url")
    private String iconUrl; // Ảnh để int, sửa thành String cho link ảnh

    @OneToMany(mappedBy = "achievement")
    private List<AchievementUser> achievedUsers;
}
