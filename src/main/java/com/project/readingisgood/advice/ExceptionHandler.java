package com.project.readingisgood.advice;

import com.project.readingisgood.exception.BookNotFoundException;
import com.project.readingisgood.exception.CustomerNotFoundException;
import com.project.readingisgood.exception.EmailConflictException;
import com.project.readingisgood.exception.EmailValidationException;
import com.project.readingisgood.exception.OrderBadRequestException;
import com.project.readingisgood.exception.OrderDetailBadRequestException;
import com.project.readingisgood.exception.OrderNotFoundException;
import com.project.readingisgood.exception.PriceNotFoundException;
import com.project.readingisgood.exception.QuantityBadRequestException;
import com.project.readingisgood.exception.StockBadRequestException;
import com.project.readingisgood.exception.StockNotFoundException;
import com.project.readingisgood.exception.TokenTimeoutException;
import com.project.readingisgood.exception.TokenUnauthorizedException;
import com.project.readingisgood.exception.UserNotFoundException;
import com.project.readingisgood.exception.UserPasswordBadRequestException;
import com.project.readingisgood.exception.model.BookNotFound;
import com.project.readingisgood.exception.model.CustomerNotFound;
import com.project.readingisgood.exception.model.EmailConflict;
import com.project.readingisgood.exception.model.EmailNotValid;
import com.project.readingisgood.exception.model.InternalServerError;
import com.project.readingisgood.exception.model.OrderBadRequest;
import com.project.readingisgood.exception.model.OrderDetailBadRequest;
import com.project.readingisgood.exception.model.OrderNotFound;
import com.project.readingisgood.exception.model.PriceNotFound;
import com.project.readingisgood.exception.model.QuantityBadRequest;
import com.project.readingisgood.exception.model.StockBadRequest;
import com.project.readingisgood.exception.model.StockNotFound;
import com.project.readingisgood.exception.model.TokenTimeout;
import com.project.readingisgood.exception.model.TokenUnauthorized;
import com.project.readingisgood.exception.model.UserNotFound;
import com.project.readingisgood.exception.model.UserPasswordBadRequest;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(com.project.readingisgood.exception.InternalServerError.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public InternalServerError handleSecurityException(com.project.readingisgood.exception.InternalServerError internalServerError) {
        return InternalServerError.builder()
                .errorCode(internalServerError.getErrorCode())
                .errorTime(internalServerError.getErrorTime())
                .cause(internalServerError.getThrowable().getClass().getName())
                .build();
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(BookNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public BookNotFound handleSecurityException(BookNotFoundException bookNotFoundException) {
        return BookNotFound.builder()
                .errorCode(bookNotFoundException.getErrorCode())
                .errorMessage(bookNotFoundException.getErrorMessage())
                .bookId(bookNotFoundException.getBookId())
                .build();
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(CustomerNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public CustomerNotFound handleSecurityException(CustomerNotFoundException customerNotFoundException) {
        return CustomerNotFound.builder()
                .errorCode(customerNotFoundException.getErrorCode())
                .errorMessage(customerNotFoundException.getErrorMessage())
                .customerId(customerNotFoundException.getCustomerId())
                .build();
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(EmailValidationException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public EmailNotValid handleSecurityException(EmailValidationException emailValidationException) {
        return EmailNotValid.builder()
                .errorCode(emailValidationException.getErrorCode())
                .errorMessage(emailValidationException.getErrorMessage())
                .email(emailValidationException.getEmail())
                .build();
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(EmailConflictException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.CONFLICT)
    public EmailConflict handleSecurityException(EmailConflictException emailConflictException) {
        return EmailConflict.builder()
                .errorCode(emailConflictException.getErrorCode())
                .errorMessage(emailConflictException.getErrorMessage())
                .email(emailConflictException.getEmail())
                .build();
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(OrderBadRequestException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public OrderBadRequest handleSecurityException(OrderBadRequestException orderBadRequestException) {
        return OrderBadRequest.builder()
                .errorCode(orderBadRequestException.getErrorCode())
                .errorMessage(orderBadRequestException.getErrorMessage())
                .build();
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(OrderDetailBadRequestException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public OrderDetailBadRequest handleSecurityException(OrderDetailBadRequestException orderDetailBadRequestException) {
        return OrderDetailBadRequest.builder()
                .errorCode(orderDetailBadRequestException.getErrorCode())
                .errorMessage(orderDetailBadRequestException.getErrorMessage())
                .build();
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(OrderNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public OrderNotFound handleSecurityException(OrderNotFoundException orderNotFoundException) {
        return OrderNotFound.builder()
                .errorCode(orderNotFoundException.getErrorCode())
                .errorMessage(orderNotFoundException.getErrorMessage())
                .build();
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(PriceNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public PriceNotFound handleSecurityException(PriceNotFoundException priceNotFoundException) {
        return PriceNotFound.builder()
                .errorCode(priceNotFoundException.getErrorCode())
                .errorMessage(priceNotFoundException.getErrorMessage())
                .build();
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(QuantityBadRequestException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public QuantityBadRequest handleSecurityException(QuantityBadRequestException quantityBadRequestException) {
        return QuantityBadRequest.builder()
                .errorCode(quantityBadRequestException.getErrorCode())
                .errorMessage(quantityBadRequestException.getErrorMessage())
                .bookId(quantityBadRequestException.getBookId())
                .build();
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(StockBadRequestException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public StockBadRequest handleSecurityException(StockBadRequestException stockBadRequestException) {
        return StockBadRequest.builder()
                .errorCode(stockBadRequestException.getErrorCode())
                .errorMessage(stockBadRequestException.getErrorMessage())
                .bookId(stockBadRequestException.getBookId())
                .build();
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(StockNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public StockNotFound handleSecurityException(StockNotFoundException stockNotFoundException) {
        return StockNotFound.builder()
                .errorCode(stockNotFoundException.getErrorCode())
                .errorMessage(stockNotFoundException.getErrorMessage())
                .build();
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(TokenTimeoutException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.REQUEST_TIMEOUT)
    public TokenTimeout handleSecurityException(TokenTimeoutException tokenTimeoutException) {
        return TokenTimeout.builder()
                .errorCode(tokenTimeoutException.getErrorCode())
                .errorMessage(tokenTimeoutException.getErrorMessage())
                .build();
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(TokenUnauthorizedException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public TokenUnauthorized handleSecurityException(TokenUnauthorizedException tokenUnauthorizedException) {
        return TokenUnauthorized.builder()
                .errorCode(tokenUnauthorizedException.getErrorCode())
                .errorMessage(tokenUnauthorizedException.getErrorMessage())
                .build();
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(UserNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public UserNotFound handleSecurityException(UserNotFoundException userNotFoundException) {
        return UserNotFound.builder()
                .errorCode(userNotFoundException.getErrorCode())
                .errorMessage(userNotFoundException.getErrorMessage())
                .username(userNotFoundException.getUsername())
                .build();
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(UserPasswordBadRequestException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public UserPasswordBadRequest handleSecurityException(UserPasswordBadRequestException userPasswordBadRequestException) {
        return UserPasswordBadRequest.builder()
                .errorCode(userPasswordBadRequestException.getErrorCode())
                .errorMessage(userPasswordBadRequestException.getErrorMessage())
                .build();
    }


}
