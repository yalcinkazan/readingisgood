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
@ApiModel(description = "Customer Registration Request")
public class CustomerRegisterRequest {
    @ApiModelProperty(notes = "Customer E-Mail")
    private String email;
    @ApiModelProperty(notes = "Customer Name")
    private String name;
    @ApiModelProperty(notes = "Customer Surname")
    private String surname;
}
