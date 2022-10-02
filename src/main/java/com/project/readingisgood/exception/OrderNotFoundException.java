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
@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Order Not Found")
public class OrderNotFoundException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    private Integer errorCode;
    private String errorMessage;

    public OrderNotFoundException() {
        super();
        this.errorCode = HttpStatus.NOT_FOUND.value();
        this.errorMessage = "Order not found";
    }

}
