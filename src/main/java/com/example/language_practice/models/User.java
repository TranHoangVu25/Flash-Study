package com.example.language_practice.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users") // 'user' thường là từ khóa trong SQL, nên đặt là 'users'
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id",nullable = false)
    private Long userId;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(name = "full_name")
    private String fullName;

//    @Enumerated(EnumType.STRING)
    private String role = "USER"; // Nên tạo : ADMIN, USER

    @Column(name = "confirmation_token")
    private String confirmationToken;

    @Column(name = "confirmation_at")
    private LocalDateTime confirmationAt;

    @Column(name = "confirmation_sent_at")
    private LocalDateTime confirmationSentAt;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // Logic đăng nhập
    @Column(name = "sign_in_count")
    private Integer signInCount;

    @Column(name = "current_sign_in_at")
    private LocalDateTime currentSignInAt;

    @Column(name = "last_sign_in_at")
    private LocalDateTime lastSignInAt;

    @Column(name = "locked_at")
    private LocalDateTime lockedAt;

    @Column(name = "is_lock")
    private Boolean isLock = false;

    // Relationships

    // 1. User tạo ra nhiều Set
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<StudySet> mySets;

    // 2. User yêu thích nhiều Set (Bảng favourite_set)
    @ManyToMany
    @JoinTable(
            name = "favourite_set",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "set_id")
    )
    private Set<StudySet> favouriteSets;

    // 3. User có nhiều Study Session
    @OneToMany(mappedBy = "user")
    private List<StudySession> studySessions;

    // 4. User nhận nhiều notification
    @OneToMany(mappedBy = "user")
    private List<Notification> notifications;

    // 5. User có nhiều report
    @OneToMany(mappedBy = "user")
    private List<Report> reports;
}
