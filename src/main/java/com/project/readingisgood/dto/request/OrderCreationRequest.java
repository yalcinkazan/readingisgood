package com.project.readingisgood.dto.request;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@ApiModel(description = "Order Creation Request")
public class OrderCreationRequest {
    @ApiModelProperty(notes = "Customer Id")
    private Long customer;
    @ApiModelProperty(notes = "Order Detail List")
    private ArrayList<OrderDetailRequest> orderContent;
}
