package com.microservice.Account.service.client;

import com.microservice.Account.dto.CardsDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name="cards", fallback = CardsFallback.class)
public interface CardsFeignClient {

    @GetMapping("api/fetch")
    ResponseEntity<List<CardsDto>> fetchCards(@RequestHeader("bank-correlation-id") String correlationId,
                                              @RequestParam String mobileNumber);
}
