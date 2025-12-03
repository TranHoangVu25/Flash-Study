package com.example.language_practice.repositories;

import com.example.language_practice.models.Achievement;
import com.example.language_practice.models.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AchievementRepository extends JpaRepository<Achievement,Long> {
    List<Achievement> findByName(String name);

    boolean existsByName(String name);
}
