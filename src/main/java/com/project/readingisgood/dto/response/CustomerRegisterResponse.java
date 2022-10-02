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
@ApiModel(description = "Customer Registration Response")
public class CustomerRegisterResponse {
    @ApiModelProperty(notes = "Customer Id")
    private Long id;
    @ApiModelProperty(notes = "Customer Name")
    private String name;
    @ApiModelProperty(notes = "Customer Surname")
    private String surname;
    @ApiModelProperty(notes = "Customer E-mail")
    private String email;
    @ApiModelProperty(notes = "Customer Creation Time")
    @JsonFormat(pattern = DateConstants.PATTERN, timezone = DateConstants.TIMEZONE)
    private Date created;

}
