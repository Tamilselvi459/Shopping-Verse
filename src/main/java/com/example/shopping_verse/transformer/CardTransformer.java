package com.example.shopping_verse.transformer;

import com.example.shopping_verse.DTO.RequestDto.CardRequestDto;
import com.example.shopping_verse.DTO.ResponseDto.CardResponseDto;
import com.example.shopping_verse.model.Card;

public class CardTransformer {

    public static Card cardRequestDtoToCard(CardRequestDto cardRequestDto){
        Card card = Card.builder()
                .cardNo(cardRequestDto.getCardNo())
                .cvv(cardRequestDto.getCvv())
                .validTill(cardRequestDto.getValidTill())
                .cardType(cardRequestDto.getCardType())
                .build();
        return card;
    }

    public static CardResponseDto cardToCardResponseDto(Card card){
        CardResponseDto cardResponseDto = CardResponseDto.builder()
                            .customerName(card.getCustomer().getName())
                             .cardType(card.getCardType())
                              .validTill(card.getValidTill())
                              .build();
        return cardResponseDto;
    }
}
