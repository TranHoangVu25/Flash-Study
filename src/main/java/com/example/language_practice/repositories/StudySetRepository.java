package com.example.language_practice.repositories;

import com.example.language_practice.models.StudySet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudySetRepository extends JpaRepository<StudySet,Long> {
}
