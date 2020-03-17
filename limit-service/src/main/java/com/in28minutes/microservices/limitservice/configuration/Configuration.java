package com.in28minutes.microservices.limitservice.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("limit-service")
@Getter
@Setter
public class Configuration {
    private Integer minimum;
    private Integer maximum;
}