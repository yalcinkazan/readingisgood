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
@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Order Bad Request")
public class OrderBadRequestException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    private Integer errorCode;
    private String errorMessage;

    public OrderBadRequestException() {
        super();
        this.errorCode = HttpStatus.BAD_REQUEST.value();
        this.errorMessage = "Missing order information";
    }

}
