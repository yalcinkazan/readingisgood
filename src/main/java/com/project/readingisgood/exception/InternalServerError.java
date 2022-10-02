package com.project.readingisgood.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Internal Server Error")
public class InternalServerError extends InternalError {

    private static final long serialVersionUID = 1L;

    private Date errorTime;
    private Integer errorCode;
    private Throwable throwable;

    public InternalServerError(Date errorTime, Throwable throwable) {
        super(throwable);
        this.errorTime = errorTime;
        this.errorCode = HttpStatus.INTERNAL_SERVER_ERROR.value();
        this.throwable = throwable;
    }

    public InternalServerError(Date errorTime) {
        this.errorTime = errorTime;
        this.errorCode = HttpStatus.INTERNAL_SERVER_ERROR.value();
    }

}