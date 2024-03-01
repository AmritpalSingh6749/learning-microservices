package com.microservice.Cards.service;

import com.microservice.Cards.dto.CardsDto;

import java.util.List;

public interface ICardsService {
    List<CardsDto> fetchCardsByMobileNumber(String mobileNumber);

    void createCard(CardsDto cardsDto);

    void updateCard(CardsDto cardsDto);
    void deleteCard(String cardNumber);
}
