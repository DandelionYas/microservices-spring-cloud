package com.in28minutes.microservices.currencyexchangeservice.controller;

import com.in28minutes.microservices.currencyexchangeservice.model.ExchangeValue;
import com.in28minutes.microservices.currencyexchangeservice.repository.ExchangeValueRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyServiceController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final ExchangeValueRepository repository;
    private final Environment environment;

    @Autowired
    public CurrencyServiceController(ExchangeValueRepository repository, Environment environment) {
        this.repository = repository;
        this.environment = environment;
    }


    @GetMapping("currency-exchange/from/{from}/to/{to}")
    public ExchangeValue retrieveExchangeValue(@PathVariable String from,
                                               @PathVariable String to) {
        ExchangeValue exchangeValue = repository.findByFromAndTo(from, to);
        exchangeValue.setPort(Integer.valueOf(environment.getProperty("local.server.port")));
        logger.info("{}",exchangeValue);
        return exchangeValue;
    }
}
