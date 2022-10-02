package com.project.readingisgood.exception;

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
@ResponseStatus(code = HttpStatus.REQUEST_TIMEOUT, reason = "Token Timeout")
public class TokenTimeoutException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    private Integer errorCode;
    private String errorMessage;

    public TokenTimeoutException() {
        super();
        this.errorCode = HttpStatus.REQUEST_TIMEOUT.value();
        this.errorMessage = "Token Timeout";
    }

}
