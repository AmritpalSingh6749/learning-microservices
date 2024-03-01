package com.microservice.Cards.mapper;

import com.microservice.Cards.dto.CardsDto;
import com.microservice.Cards.entity.Cards;

import java.util.Objects;

public class CardsMapper {
    private CardsMapper(){}

    public static Cards mapToCards(CardsDto cardsDto, Cards cards){
        cards.setCardType(cardsDto.getCardType());
        cards.setTotalAmount(cardsDto.getTotalLimit());
        cards.setAmountUsed(cards.getAmountUsed()+cardsDto.getAmountUsed());
        cards.setAvailableAmount(cards.getTotalAmount() - cards.getAmountUsed());
        cards.setMobileNumber(cardsDto.getMobileNumber());
        return cards;
    }

    public static CardsDto mapToCardsDto(Cards cards){
        return CardsDto.builder()
                .cardType(cards.getCardType())
                .cardNumber(cards.getCardNumber())
                .totalLimit(cards.getTotalAmount())
                .amountUsed(cards.getAmountUsed())
                .mobileNumber(cards.getMobileNumber())
                .remainingAmount(cards.getAvailableAmount())
                .build();
    }
}
