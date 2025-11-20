package com.example.language_practice.repositories;

import com.example.language_practice.models.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card,Long> {
}
