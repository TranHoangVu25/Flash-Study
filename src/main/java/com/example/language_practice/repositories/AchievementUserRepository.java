package com.example.language_practice.repositories;

import com.example.language_practice.models.Achievement;
import com.example.language_practice.models.AchievementUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AchievementUserRepository extends JpaRepository<AchievementUser,Long> {
}
