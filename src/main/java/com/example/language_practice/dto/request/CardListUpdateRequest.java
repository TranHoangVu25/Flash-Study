package com.example.language_practice.dto.request;

import com.example.language_practice.models.Card;
import jakarta.persistence.Column;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CardListUpdateRequest {
    private Long cardId;
    private String frontText;
    private String backText;
    private LocalDateTime updatedAt;
    private String frontLanguage;
    private String backLanguage;
}
