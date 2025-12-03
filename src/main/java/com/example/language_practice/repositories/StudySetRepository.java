package com.example.language_practice.repositories;

import com.example.language_practice.models.StudySet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudySetRepository extends JpaRepository<StudySet,Long> {
//    @Query(value = "select * from study_sets where user_id = userId",nativeQuery = true)
//    List<StudySet> findStudySetByUserId(
//            @Param("userId") Long userId
//    );

    List<StudySet> findByUser_UserId(Long userId);


    boolean existsByUser_UserId(Long userId);

    // Kiểm tra setId thuộc về userId
    boolean existsBySetIdAndUser_UserId(Long setId, Long userId);
}
