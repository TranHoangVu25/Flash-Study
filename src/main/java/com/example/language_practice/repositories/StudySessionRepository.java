package com.example.language_practice.repositories;

import com.example.language_practice.models.StudySession;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudySessionRepository extends JpaRepository<StudySession,Long> {
}
