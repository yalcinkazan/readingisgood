package com.project.readingisgood.exception.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class TokenTimeout extends AbstractErrorModel{

    @Builder
    public TokenTimeout(Integer errorCode, String errorMessage) {
        super(errorCode, errorMessage);
    }
}
