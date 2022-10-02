package com.project.readingisgood.service;

import com.project.readingisgood.dto.response.MonthlyStatisticsResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public interface StatisticsService {
    ArrayList<MonthlyStatisticsResponse> getMonthlyStatistics();
}
