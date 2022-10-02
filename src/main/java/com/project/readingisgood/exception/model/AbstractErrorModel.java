package com.project.readingisgood.exception.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class AbstractErrorModel {
    private Integer errorCode;
    private String errorMessage;
}
