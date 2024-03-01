package com.microservice.Cards.audit;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component(value = "auditRef")
public class AuditorRef implements AuditorAware {
    @Override
    public Optional getCurrentAuditor() {
        return Optional.of("Cards Admin");
    }
}
