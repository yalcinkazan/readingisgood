package com.project.readingisgood.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.readingisgood.constant.DateConstants;
import com.project.readingisgood.enums.OrderStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@ApiModel(description = "Order Response")
public class OrderResponse {
    @ApiModelProperty(notes = "Order Id")
    private Long id;
    @ApiModelProperty(notes = "Customer Id")
    private Long customer;
    @ApiModelProperty(notes = "Total Book Count")
    private Integer totalBookCount;
    @ApiModelProperty(notes = "Total Purchased Amount")
    private BigDecimal totalPurchasedAmount;
    @ApiModelProperty(notes = "Order Detail List")
    private List<OrderDetailResponse> orderContent;
    @ApiModelProperty(notes = "Order Creation Date")
    @JsonFormat(pattern = DateConstants.PATTERN, timezone = DateConstants.TIMEZONE)
    private Date created;
    @ApiModelProperty(notes = "Order Status")
    private OrderStatus status;
}
