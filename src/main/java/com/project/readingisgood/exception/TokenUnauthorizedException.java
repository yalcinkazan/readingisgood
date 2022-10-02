package com.project.readingisgood.exception;

import com.project.readingisgood.enums.AuthorizationStatus;
import com.project.readingisgood.enums.TokenType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@Setter
@AllArgsConstructor
@ToString
@Builder
@ResponseStatus(code = HttpStatus.UNAUTHORIZED, reason = "Token Unauthorized")
public class TokenUnauthorizedException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    private Integer errorCode;
    private String errorMessage;
    private AuthorizationStatus responseStatus;
    private TokenType tokenType;

    public TokenUnauthorizedException() {
        super();
        this.errorCode = HttpStatus.UNAUTHORIZED.value();
        this.errorMessage = "Token Unauthorized";
        this.responseStatus = AuthorizationStatus.UNAUTHORIZED;
        this.tokenType = TokenType.BEARER;
    }

}
