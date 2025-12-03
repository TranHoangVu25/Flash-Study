package com.example.language_practice.dto.request;

import com.example.language_practice.models.Card;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
//dùng trong việc tạo nhiều card
public class CardListCreateRequest {
    Long studySetId;
    List<Card> cardList;
}
