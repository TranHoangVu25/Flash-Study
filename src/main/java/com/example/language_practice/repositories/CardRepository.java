package com.example.language_practice.repositories;

import com.example.language_practice.models.Card;
import com.example.language_practice.models.StudySet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<Card,Long> {
    @Query("select c from Card c where c.studySet.setId = ?1")
    List<Card> findCardByStudySet(Long id);

//    List<Card> findByStudySet(Long id);
}
