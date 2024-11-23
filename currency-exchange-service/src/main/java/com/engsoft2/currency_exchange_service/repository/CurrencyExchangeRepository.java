package com.engsoft2.currency_exchange_service.repository;

import org.springframework.data.repository.CrudRepository;

import com.engsoft2.currency_exchange_service.model.CurrencyExchange;

public interface CurrencyExchangeRepository extends 
CrudRepository<CurrencyExchange,Long> {
    CurrencyExchange findByFromAndTo(String from, String to);
}