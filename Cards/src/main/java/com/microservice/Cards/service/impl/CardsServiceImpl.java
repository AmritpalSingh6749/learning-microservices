package com.microservice.Cards.service.impl;

import com.microservice.Cards.dto.CardsDto;
import com.microservice.Cards.entity.Cards;
import com.microservice.Cards.exception.ResourceNotFoundException;
import com.microservice.Cards.mapper.CardsMapper;
import com.microservice.Cards.repository.CardsRepository;
import com.microservice.Cards.service.ICardsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
@AllArgsConstructor
public class CardsServiceImpl implements ICardsService {
    private CardsRepository cardsRepository;

    @Override
    public List<CardsDto> fetchCardsByMobileNumber(String mobileNumber) {
        return cardsRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Cards", "mobileNumber", mobileNumber))
                .stream().map(CardsMapper::mapToCardsDto).toList();
    }

    @Override
    public void createCard(CardsDto cardsDto) {
        Cards cards = CardsMapper.mapToCards(cardsDto, new Cards());
        String cardNumber = String.valueOf((long) 1e11 + new Random().nextLong((long) 9e10));
        cards.setCardNumber(cardNumber);
        cardsRepository.save(cards);
    }

    @Override
    public void updateCard(CardsDto cardsDto) {
        Cards card = cardsRepository.findByCardNumber(cardsDto.getCardNumber())
                .orElseThrow(() -> new ResourceNotFoundException("Cards", "cardNumber", cardsDto.getCardNumber()));
        CardsMapper.mapToCards(cardsDto, card);
        cardsRepository.save(card);
    }

    @Override
    public void deleteCard(String cardNumber) {
        Cards card = cardsRepository.findByCardNumber(cardNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Cards", "cardNumber", cardNumber));
        cardsRepository.delete(card);
    }
}
