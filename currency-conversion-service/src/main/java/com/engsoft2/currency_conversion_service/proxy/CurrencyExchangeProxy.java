package com.engsoft2.currency_conversion_service.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.engsoft2.currency_conversion_service.model.CurrencyConversion;

// Removendo a "url" da anotação @FeignClient(name = "currency-exchange", url = "localhost:8000")
// para que o FeignClient busque o serviço no Eureka Server
@FeignClient(name = "currency-exchange")
public interface CurrencyExchangeProxy {

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    CurrencyConversion retrieveExchangeValue(@PathVariable String from, @PathVariable String to);
}
