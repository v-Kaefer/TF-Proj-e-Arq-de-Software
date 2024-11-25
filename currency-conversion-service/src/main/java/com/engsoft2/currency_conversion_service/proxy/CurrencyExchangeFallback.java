package com.engsoft2.currency_conversion_service.proxy;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.engsoft2.currency_conversion_service.model.CurrencyConversion;


@Component
public class CurrencyExchangeFallback implements CurrencyExchangeProxy {
    @Override
    public CurrencyConversion retrieveExchangeValue(String from, String to) {
        return new CurrencyConversion(0L,from,to,BigDecimal.ZERO,BigDecimal.ZERO,BigDecimal.ZERO,"circuit-breaker fallback");
    }
}