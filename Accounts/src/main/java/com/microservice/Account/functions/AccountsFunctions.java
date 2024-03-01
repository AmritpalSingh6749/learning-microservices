package com.microservice.Account.functions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class AccountsFunctions {

    private static final Logger logger = LoggerFactory.getLogger(AccountsFunctions.class);

    public Consumer<Long> updateCommunication(){
        return accountNumber -> {
            logger.info("updating communication status for account number {}", accountNumber.toString());
            logger.info("Table updated: ");
        };
    }

}
