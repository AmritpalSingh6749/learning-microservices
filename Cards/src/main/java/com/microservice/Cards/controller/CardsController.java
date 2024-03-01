package com.microservice.Cards.controller;

import com.microservice.Cards.constants.CardsConstants;
import com.microservice.Cards.dto.CardsContactInfoDto;
import com.microservice.Cards.dto.CardsDto;
import com.microservice.Cards.dto.ResponseDto;
import com.microservice.Cards.service.ICardsService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@EnableConfigurationProperties(CardsContactInfoDto.class)
@RestController
public class CardsController {
    private static final Logger logger = LoggerFactory.getLogger(CardsController.class);
    private ICardsService cardsService;
    private CardsContactInfoDto cardsContactInfoDto;

    @GetMapping(path = "/fetch")
    public ResponseEntity<List<CardsDto>> fetchCards(@RequestHeader("bank-correlation-id") String correlationId,
                                                     @Pattern(regexp = "$|[0-9]{10}", message = "Please enter a valid mobileNumber")
                                                     @RequestParam String mobileNumber) {
        logger.debug("bank-correlation-id found: {}", correlationId);
        List<CardsDto> cards = cardsService.fetchCardsByMobileNumber(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK)
                .body(cards);
    }

    @PostMapping(path = "/create")
    public ResponseEntity<ResponseDto> createCard(@Valid @RequestBody CardsDto cardsDto) {
        cardsService.createCard(cardsDto);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseDto(CardsConstants.STATUS_201, CardsConstants.MESSAGE_201));
    }

    @PutMapping(path = "/update")
    public ResponseEntity<ResponseDto> updateCards(@Valid @RequestBody CardsDto cardsDto) {
        cardsService.updateCard(cardsDto);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseDto(CardsConstants.STATUS_200, CardsConstants.MESSAGE_200));
    }

    @DeleteMapping(path = "/delete")
    public ResponseEntity<ResponseDto> deleteCard(@Pattern(regexp = "$|[0-9]{12}", message = "Please enter a valid cardNumber")
                                                  @RequestParam String cardNumber) {
        cardsService.deleteCard(cardNumber);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseDto(CardsConstants.STATUS_200, CardsConstants.MESSAGE_200));
    }

    @GetMapping("/contact-info")
    public ResponseEntity<CardsContactInfoDto> getContactInfo() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(cardsContactInfoDto);
    }
}
