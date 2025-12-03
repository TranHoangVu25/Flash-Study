package com.example.language_practice.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "study_sets") // Đổi tên bảng tránh keyword SQL 'SET'
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudySet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "set_id")
    private Long setId;

    @Column(name = "set_title", nullable = false)
    private String setTitle;

    private String subjects;

    private String access; // Có thể dùng Enum: PUBLIC, PRIVATE

    private String description;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "rate")
    private Integer rate;

    // Relationships

    // Người tạo set
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore //thêm vào để tránh vòng lặp vô tận
    private User user;

    // Các thẻ card trong set
    @OneToMany(mappedBy = "studySet", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Card> cards;

    // Tags của set (Bảng tag_set)
    @ManyToMany
    @JoinTable(
            name = "tag_set",
            joinColumns = @JoinColumn(name = "set_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private Set<Tag> tags;

    // Users đã like set này
    @ManyToMany(mappedBy = "favouriteSets")
    private Set<User> favoritedByUsers;
}