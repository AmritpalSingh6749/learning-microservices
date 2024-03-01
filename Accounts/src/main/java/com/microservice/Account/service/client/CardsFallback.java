package com.microservice.Account.service.client;

import com.microservice.Account.dto.CardsDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CardsFallback implements CardsFeignClient{

    @Override
    public ResponseEntity<List<CardsDto>> fetchCards(String correlationId, String mobileNumber) {
        return null;
    }
}
