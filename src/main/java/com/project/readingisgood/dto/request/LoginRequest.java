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
@ApiModel(description = "User Login Request")
public class LoginRequest {
    @ApiModelProperty(notes = "Username")
    private String username;
    @ApiModelProperty(notes = "Password")
    private String password;
}
