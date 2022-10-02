package com.project.readingisgood.dto.request;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@ApiModel(description = "Order Detail Request")
public class OrderDetailRequest {
    @ApiModelProperty(notes = "Book Id")
    private Long book;
    @ApiModelProperty(notes = "Book Quantity")
    private Integer quantity;
}
