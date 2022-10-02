package com.project.readingisgood.dto.request;

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
@ApiModel(description = "Book Creation Request")
public class BookCreationRequest {
    @ApiModelProperty(notes = "Book Name")
    private String name;
    @ApiModelProperty(notes = "Book Author")
    private String author;
    @ApiModelProperty(notes = "Book Price")
    private BigDecimal price;
}
