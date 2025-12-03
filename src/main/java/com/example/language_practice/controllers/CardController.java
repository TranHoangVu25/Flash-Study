package com.example.language_practice.controllers;

import com.example.language_practice.dto.request.CardListCreateRequest;
import com.example.language_practice.dto.request.CardListUpdateRequest;
import com.example.language_practice.dto.response.ApiResponse;
import com.example.language_practice.models.Card;
import com.example.language_practice.services.CardService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("api/v1/cards")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CardController {
    CardService cardService;

    @GetMapping("/{studySetId}")
    public ResponseEntity<ApiResponse<List<Card>>> getAllCards(
            @PathVariable("studySetId") Long studySetId
    ) {
        return cardService.getAllCard(studySetId);
    }

    @PostMapping("/{studySetId}")
    public ResponseEntity<ApiResponse<List<Card>>> addCardList(
            @RequestBody List<Card> cardList,
            @PathVariable("studySetId") Long studySetId
    ) {
        CardListCreateRequest cardListCreateRequest = new CardListCreateRequest(studySetId,cardList);
        return cardService.addCard(cardListCreateRequest);
    }

    @PutMapping("/{setId}")
    public ResponseEntity<ApiResponse<List<Card>>> updateCardList(
            @RequestBody List<CardListUpdateRequest> cardListUpdateRequest,
            @PathVariable Long setId
    ) {
        return cardService.updateCard(setId, cardListUpdateRequest);
    }

    @DeleteMapping("/{cardId}")
    public ResponseEntity<ApiResponse<String>> deleteCardList(
            @PathVariable Long cardId
    ) {
        return cardService.deleteStudy(cardId);
    }
}
