package com.engsoft2.currency_conversion_service.controller;

import java.math.BigDecimal;
import java.util.HashMap;

import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.engsoft2.currency_conversion_service.exception.ResourceNotFoundException;
import com.engsoft2.currency_conversion_service.model.CurrencyConversion;
import com.engsoft2.currency_conversion_service.proxy.CurrencyExchangeProxy;

import main.java.com.engsoft2.currency_conversion_service.HistoryDTO;

@RestController
public class CurrencyConversionController {

    private final CurrencyExchangeProxy proxy;
    private RabbitTemplate rabbitTemplate;
    private FanoutExchange fanout;
  
    public CurrencyConversionController(CurrencyExchangeProxy proxy, RabbitTemplate template, FanoutExchange fanout) {
        this.proxy = proxy;
        this.rabbitTemplate = template;
        this.fanout = fanout;
    }

    public CurrencyConversionController(CurrencyExchangeProxy proxy) {
        this.proxy = proxy;
    }

    @GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion calculateCurrencyConversion(
            @PathVariable String from,
            @PathVariable String to,
            @PathVariable BigDecimal quantity) {

        HashMap<String, String> uriVariables = new HashMap<>();
        uriVariables.put("from", from);
        uriVariables.put("to", to);

        ResponseEntity<CurrencyConversion> responseEntity = new RestTemplate().getForEntity(
                "http://localhost:8000/currency-exchange/from/{from}/to/{to}",
                CurrencyConversion.class,
                uriVariables
        );

        if (responseEntity.getBody() == null) {
            throw new ResourceNotFoundException("Exchange rate from " + from + " to " + to + " not found.");
        }

        CurrencyConversion currencyConversion = responseEntity.getBody();
        BigDecimal total = quantity.multiply(currencyConversion.getConversionMultiple());

        return new CurrencyConversion(
                currencyConversion.getId(),
                from,
                to,
                quantity,
                currencyConversion.getConversionMultiple(),
                total,
                currencyConversion.getEnvironment() + " via RestTemplate"
        );
    }

    @GetMapping("/currency-conversion-feign/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion calculateCurrencyConversionFeign(
            @PathVariable String from,
            @PathVariable String to,
            @PathVariable BigDecimal quantity) {

        CurrencyConversion currencyConversion = proxy.retrieveExchangeValue(from, to);
        HistoryDTO dto = new HistoryDTO(from, to);
        rabbitTemplate.convertAndSend(fanout.getName(),"",dto);
        BigDecimal total = quantity.multiply(currencyConversion.getConversionMultiple()); //?

        return new CurrencyConversion(
                currencyConversion.getId(),
                from,
                to,
                quantity,
                currencyConversion.getConversionMultiple(),
                total,
                currencyConversion.getEnvironment() + " via Feign"
        );
    }
}
