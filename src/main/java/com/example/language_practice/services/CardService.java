package com.example.language_practice.services;

import com.example.language_practice.dto.request.CardListCreateRequest;
import com.example.language_practice.dto.request.CardListUpdateRequest;
import com.example.language_practice.dto.response.ApiResponse;
import com.example.language_practice.models.Card;
import com.example.language_practice.models.StudySet;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CardService {
    ResponseEntity<ApiResponse<List<Card>>> getAllCard(Long studySetId);

    ResponseEntity<ApiResponse<List<Card>>> addCard(CardListCreateRequest request);

    ResponseEntity<ApiResponse<List<Card>>> updateCard(Long studySetId, List<CardListUpdateRequest> requests);

    ResponseEntity<ApiResponse<String>> deleteStudy(Long cardId);
}
