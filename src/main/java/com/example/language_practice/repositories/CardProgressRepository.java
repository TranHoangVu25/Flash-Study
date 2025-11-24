package com.example.language_practice.repositories;

import com.example.language_practice.models.CardProgress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardProgressRepository extends JpaRepository<CardProgress,Long> {

}
