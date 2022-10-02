package com.project.readingisgood.exception.model;

import com.project.readingisgood.enums.AuthorizationStatus;
import com.project.readingisgood.enums.TokenType;
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
public class TokenUnauthorized extends AbstractErrorModel{
    private AuthorizationStatus responseStatus;
    private TokenType tokenType;

    @Builder
    public TokenUnauthorized(AuthorizationStatus responseStatus, TokenType tokenType, Integer errorCode, String errorMessage) {
        super(errorCode, errorMessage);
        this.responseStatus = responseStatus;
        this.tokenType = tokenType;
    }

}
