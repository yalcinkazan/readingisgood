package com.project.readingisgood.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.readingisgood.constant.DateConstants;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@ApiModel(description = "Book Stock Expand Operation Response")
public class BookStockExpandResponse {
    @ApiModelProperty(notes = "Book Id")
    private Long id;
    @ApiModelProperty(notes = "Current Book Stock Quantity")
    private Integer quantity;
    @ApiModelProperty(notes = "Response Message")
    private String message;
    @ApiModelProperty(notes = "Book Stock Expand Operation Time")
    @JsonFormat(pattern = DateConstants.PATTERN, timezone = DateConstants.TIMEZONE)
    private Date time;
}
