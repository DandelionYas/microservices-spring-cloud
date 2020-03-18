package com.in28minutes.microservices.currencyexchangeservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExchangeValue {
    @Id
    private Long id;
    @Column(name = "CURRENCY_FROM")
    private String from;
    @Column(name = "CURRENCY_TO")
    private String to;
    private BigDecimal conversionMultiple;
    private Integer port;// To determine which instance of service calculated the ExchangeValue
}
