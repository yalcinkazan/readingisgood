package com.project.readingisgood.exception.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.readingisgood.constant.DateConstants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class InternalServerError {
    @JsonFormat(pattern = DateConstants.PATTERN, timezone = DateConstants.TIMEZONE)
    private Date errorTime;
    private Integer errorCode;
    private String cause;
}