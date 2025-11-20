package com.example.language_practice.repositories;

import com.example.language_practice.models.Achievement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AchievementRepository extends JpaRepository<Achievement,Long> {
}
