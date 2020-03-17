package com.in28minutes.microservices.limitservice.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LimitConfiguration {
    private Integer minimum;
    private Integer maximum;

    public LimitConfiguration(Integer minimum, Integer maximum) {
        this.minimum = minimum;
        this.maximum = maximum;
    }
}
