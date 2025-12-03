package com.example.language_practice.services.implement;

import com.example.language_practice.dto.request.CardListCreateRequest;
import com.example.language_practice.dto.request.CardListUpdateRequest;
import com.example.language_practice.dto.response.ApiResponse;
import com.example.language_practice.exception.ErrorCode;
import com.example.language_practice.models.Card;
import com.example.language_practice.models.StudySet;
import com.example.language_practice.repositories.CardRepository;
import com.example.language_practice.repositories.StudySetRepository;
import com.example.language_practice.services.CardService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class CardServiceImpl implements CardService {
    CardRepository cardRepository;
    StudySetRepository studySetRepository;

    @Override
    public ResponseEntity<ApiResponse<List<Card>>> getAllCard(Long studySetId) {
        List<Card> list = cardRepository.findCardByStudySet(studySetId);
        if(list.isEmpty()){
            return ResponseEntity.ok()
                    .body(
                            ApiResponse.<List<Card>>builder()
                                    .message("List card set is empty.")
                                    .build()
                    );
        }
        return ResponseEntity.ok()
                .body(
                        ApiResponse.<List<Card>>builder()
                                .result(list)
                                .build()
                );
    }

    @Override
    public ResponseEntity<ApiResponse<List<Card>>> addCard(CardListCreateRequest request) {
        if (!studySetRepository.existsById(request.getStudySetId())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(
                            ApiResponse.<List<Card>>builder()
                                    .message(ErrorCode.STUDY_SET_NOT_EXISTED.getMessage())
                                    .build()
                    );
        }

        StudySet set = studySetRepository.findById(request.getStudySetId())
                .orElseThrow(() -> new RuntimeException("Set not found"));

        List<Card> cards = request.getCardList().stream().map(
                c->{
                    Card card = new Card();
                    card.setStudySet(set);
                    card.setFrontText(c.getFrontText());
                    card.setBackText(c.getBackText());
                    card.setFrontLanguage(c.getFrontLanguage());
                    card.setBackLanguage(c.getBackLanguage());
                    card.setCreatedAt(LocalDateTime.now());
                    card.setUpdatedAt(LocalDateTime.now());
                    return card;
        }).toList();

        List<Card> lst = cardRepository.saveAll(cards);
        return ResponseEntity.ok()
                .body(
                        ApiResponse.<List<Card>>builder()
                                .result(lst)
                                .build()
                );
    }

    @Override
    public ResponseEntity<ApiResponse<List<Card>>> updateCard(Long studySetId, List<CardListUpdateRequest>requests) {
        if (!studySetRepository.existsById(studySetId)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(
                            ApiResponse.<List<Card>>builder()
                                    .message(ErrorCode.STUDY_SET_NOT_EXISTED.getMessage())
                                    .build()
                    );
        }
        List<Card> updateRequests = requests.stream().map(
                r->{

                    Card card = cardRepository.findById(r.getCardId())
                            .orElseThrow(() -> new RuntimeException("Card not found"));
                    card.setUpdatedAt(LocalDateTime.now());
                    card.setFrontLanguage(r.getFrontLanguage());
                    card.setBackLanguage(r.getBackLanguage());
                    card.setFrontText(r.getFrontText());
                    card.setBackText(r.getBackText());
                    return card;
                }).toList();

        List<Card> lst = cardRepository.saveAll(updateRequests);

        return ResponseEntity.ok()
                .body(
                        ApiResponse.<List<Card>>builder()
                                .message("Update card list successfully.")
                                .result(lst)
                                .build()
                );
    }

    @Override
    public ResponseEntity<ApiResponse<String>> deleteStudy(Long cardId) {
        if (!cardRepository.existsById(cardId)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(
                            ApiResponse.<String>builder()
                                    .message(ErrorCode.CARD_NOT_EXISTED.getMessage())
                                    .build()
                    );
        }
        cardRepository.deleteById(cardId);
        return ResponseEntity.ok()
                .body(
                        ApiResponse.<String>builder()
                                .message("Delete card successfully.")
                                .build()
                );
    }
}
