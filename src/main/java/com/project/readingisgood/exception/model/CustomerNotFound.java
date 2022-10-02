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
public class CustomerNotFound extends AbstractErrorModel {
    private Long customerId;

    @Builder
    public CustomerNotFound(Long customerId, Integer errorCode, String errorMessage) {
        super(errorCode, errorMessage);
        this.customerId = customerId;
    }

}
