package com.project.readingisgood.exception.model;

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
public class QuantityBadRequest extends AbstractErrorModel {
    private Long bookId;

    @Builder
    public QuantityBadRequest(Long bookId, Integer errorCode, String errorMessage) {
        super(errorCode, errorMessage);
        this.bookId = bookId;
    }

}