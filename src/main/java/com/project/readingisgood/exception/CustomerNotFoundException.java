package com.project.readingisgood.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Customer Not Found")
public class CustomerNotFoundException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    private Integer errorCode;
    private String errorMessage;
    private Long customerId;

    public CustomerNotFoundException(Long customerId) {
        super();
        this.errorCode = HttpStatus.NOT_FOUND.value();
        this.errorMessage = "Customer Not Found";
        this.customerId = customerId;
    }

}
