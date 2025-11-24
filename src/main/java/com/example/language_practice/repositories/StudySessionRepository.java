package com.example.language_practice.repositories;

import com.example.language_practice.models.StudySession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudySessionRepository extends JpaRepository<StudySession,Long> {
}
