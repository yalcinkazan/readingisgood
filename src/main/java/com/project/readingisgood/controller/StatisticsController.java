package com.project.readingisgood.controller;

import com.project.readingisgood.dto.response.MonthlyStatisticsResponse;
import com.project.readingisgood.exception.model.InternalServerError;
import com.project.readingisgood.exception.model.OrderNotFound;
import com.project.readingisgood.exception.model.TokenUnauthorized;
import com.project.readingisgood.service.StatisticsService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/statistics")
public class StatisticsController {

    @Autowired
    private StatisticsService statisticsService;

    @ApiOperation(value = "Get Monthly Statistics", response = MonthlyStatisticsResponse.class, authorizations = { @Authorization(value="jwtToken") })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Monthly Statistics Got Successfully", response = MonthlyStatisticsResponse.class),
            @ApiResponse(code = 401, message = "Token Unauthorized", response = TokenUnauthorized.class),
            @ApiResponse(code = 404, message = "Order Not Found", response = OrderNotFound.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = InternalServerError.class),
    })
    @GetMapping("/monthly")
    public List<MonthlyStatisticsResponse> getMonthlyStatistics(){
        return this.statisticsService.getMonthlyStatistics();
    }



}
