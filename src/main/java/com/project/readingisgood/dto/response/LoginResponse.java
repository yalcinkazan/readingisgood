package com.project.readingisgood.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.readingisgood.constant.DateConstants;
import com.project.readingisgood.enums.AuthorizationStatus;
import com.project.readingisgood.enums.TokenType;
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
@ApiModel(description = "User Login Response")
public class LoginResponse {
    @ApiModelProperty(notes = "Login Message")
    private String message;
    @ApiModelProperty(notes = "Response Status")
    private AuthorizationStatus responseStatus;
    @ApiModelProperty(notes = "Authentication Token")
    private String token;
    @ApiModelProperty(notes = "Token Type")
    private TokenType tokenType;
    @ApiModelProperty(notes = "Login Time")
    @JsonFormat(pattern = DateConstants.PATTERN, timezone = DateConstants.TIMEZONE)
    private Date time;
}
