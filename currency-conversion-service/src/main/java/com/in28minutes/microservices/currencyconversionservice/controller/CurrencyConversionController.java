package com.in28minutes.microservices.currencyconversionservice.controller;

import com.in28minutes.microservices.currencyconversionservice.model.CurrencyConversionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RestController
public class CurrencyConversionController {
    @Autowired
    private CurrencyExchangeServiceProxy proxy;

    @GetMapping("/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversionDto currencyConverter(@PathVariable String from,
                                                   @PathVariable String to,
                                                   @PathVariable BigDecimal quantity) {
        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("from", from);
        uriVariables.put("to", to);
        ResponseEntity<CurrencyConversionDto> responseEntity = new RestTemplate()
                .getForEntity("http://localhost:8000/currency-exchange/from/{from}/to/{to}",
                        CurrencyConversionDto.class, uriVariables);
        CurrencyConversionDto response = responseEntity.getBody();
        return new CurrencyConversionDto(response.getId(), from, to,
                response.getConversionMultiple(), quantity,
                quantity.multiply(response.getConversionMultiple()), response.getPort());
    }

    @GetMapping("/currency-converter-feign/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversionDto currencyConverterFeign(@PathVariable String from,
                                                   @PathVariable String to,
                                                   @PathVariable BigDecimal quantity) {
        CurrencyConversionDto response = proxy.retrieveExchangeValue(from, to);
        return new CurrencyConversionDto(response.getId(), from, to,
                response.getConversionMultiple(), quantity,
                quantity.multiply(response.getConversionMultiple()), response.getPort());
    }
}