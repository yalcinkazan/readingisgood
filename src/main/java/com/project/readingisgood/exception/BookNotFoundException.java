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
@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Book Not Found")
public class BookNotFoundException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    private Integer errorCode;
    private String errorMessage;
    private Long bookId;

    public BookNotFoundException(Long bookId) {
        super();
        this.errorCode = HttpStatus.NOT_FOUND.value();
        this.errorMessage = "Book Not Found";
        this.bookId = bookId;
    }

}
