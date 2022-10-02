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
public class EmailConflict extends AbstractErrorModel {
    private String email;

    @Builder
    public EmailConflict(String email, Integer errorCode, String errorMessage) {
        super(errorCode, errorMessage);
        this.email = email;
    }

}