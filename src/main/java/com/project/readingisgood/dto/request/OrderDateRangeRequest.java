package com.project.readingisgood.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(description = "Order By Date Range Request")
public class OrderDateRangeRequest {
    @ApiModelProperty(notes = "Start Date As YYYY-MM-DD")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date startDate;
    @ApiModelProperty(notes = "End Date As YYYY-MM-DD")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date endDate;
}
