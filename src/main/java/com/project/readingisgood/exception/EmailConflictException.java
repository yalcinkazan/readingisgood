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
@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Email Conflict")
public class EmailConflictException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    private Integer errorCode;
    private String errorMessage;
    private String email;

    public EmailConflictException(String email) {
        super();
        this.errorCode = HttpStatus.CONFLICT.value();
        this.errorMessage = "This email already in use";
        this.email = email;
    }

}
