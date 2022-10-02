package com.project.readingisgood.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Statistics {

    private Integer month;
    private Integer totalOrderCount;
    private Integer totalBookCount;
    private BigDecimal totalPurchaseAmount;

}
