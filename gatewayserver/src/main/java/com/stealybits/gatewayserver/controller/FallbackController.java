package com.stealybits.gatewayserver.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController

public class FallbackController {
    @RequestMapping("/oontactSupport")
    public Mono<String> contactSupport() {
        return Mono.just("An error occurred. Please try after some time or contact support team!!!");
    }
}
