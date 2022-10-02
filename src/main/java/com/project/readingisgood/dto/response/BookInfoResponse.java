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

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@ApiModel(description = "Book Info Response")
public class BookInfoResponse {
    @ApiModelProperty(notes = "Book Id")
    private Long id;
    @ApiModelProperty(notes = "Book Name")
    private String name;
    @ApiModelProperty(notes = "Book Author")
    private String author;
    @ApiModelProperty(notes = "Book Stock Quantity")
    private Integer quantity;
    @ApiModelProperty(notes = "Book Price")
    private BigDecimal price;
    @ApiModelProperty(notes = "Book Created Date")
    @JsonFormat(pattern = DateConstants.PATTERN, timezone = DateConstants.TIMEZONE)
    private Date created;
}
