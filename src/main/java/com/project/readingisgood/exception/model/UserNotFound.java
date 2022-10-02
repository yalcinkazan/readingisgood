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
public class UserNotFound extends AbstractErrorModel{
    private String username;

    @Builder
    public UserNotFound(String username, Integer errorCode, String errorMessage) {
        super(errorCode, errorMessage);
        this.username = username;
    }
}
