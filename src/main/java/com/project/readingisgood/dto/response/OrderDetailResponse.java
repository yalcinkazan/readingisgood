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
@ApiModel(description = "Order Detail Response")
public class OrderDetailResponse {
    @ApiModelProperty(notes = "Book Id")
    private Long book;
    @ApiModelProperty(notes = "Book Quantity")
    private Integer quantity;
    @ApiModelProperty(notes = "Total Amount")
    private BigDecimal amount;
}
