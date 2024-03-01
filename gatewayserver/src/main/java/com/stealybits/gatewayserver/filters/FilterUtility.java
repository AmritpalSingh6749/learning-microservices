package com.stealybits.gatewayserver.filters;

import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

@Component
public class FilterUtility {

    public static final String CORRELATION_ID = "bank-correlation-id";

    public String getCorrelationId(HttpHeaders requestHeaders) {
        return requestHeaders.get(CORRELATION_ID) != null ? requestHeaders.get(CORRELATION_ID).stream().findFirst().get()
                : null;
    }

    public ServerWebExchange setCorrelationId(ServerWebExchange exchange, String correlationId) {
        ServerHttpRequest request = exchange.getRequest().mutate().header(CORRELATION_ID, correlationId).build();
        return exchange.mutate().request(request).build();
    }
}
