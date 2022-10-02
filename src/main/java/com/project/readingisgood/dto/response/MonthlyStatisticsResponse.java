package com.project.readingisgood.dto.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@ApiModel(description = "Monthly Statistics Response")
public class MonthlyStatisticsResponse {
    @ApiModelProperty(notes = "Statistics Month")
    private String month;
    @ApiModelProperty(notes = "Total Order Count")
    private Long totalOrderCount;
    @ApiModelProperty(notes = "Total Book Count")
    private Integer totalBookCount;
    @ApiModelProperty(notes = "Total Purchased Amount")
    private BigDecimal totalPurchasedAmount;
}
