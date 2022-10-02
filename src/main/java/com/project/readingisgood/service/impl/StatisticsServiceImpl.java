package com.project.readingisgood.service.impl;

import com.project.readingisgood.dto.response.MonthlyStatisticsResponse;
import com.project.readingisgood.dto.response.OrderResponse;
import com.project.readingisgood.entity.Orders;
import com.project.readingisgood.exception.InternalServerError;
import com.project.readingisgood.exception.OrderNotFoundException;
import com.project.readingisgood.service.AbstractService;
import com.project.readingisgood.service.OrderService;
import com.project.readingisgood.service.StatisticsService;
import com.project.readingisgood.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StatisticsServiceImpl extends AbstractService implements StatisticsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(StatisticsServiceImpl.class);

    @Autowired
    private OrderService orderService;

    @Override
    public ArrayList<MonthlyStatisticsResponse> getMonthlyStatistics() {
        try {
            Optional<List<Orders>> optionalOrdersList = this.orderService.findAll();
            if(!optionalOrdersList.isPresent() || optionalOrdersList.get().isEmpty()) throw new OrderNotFoundException();
            List<OrderResponse> orderResponseList = optionalOrdersList.get().stream().parallel().map(order ->
                    this.orderService.getOrderById(order.getId())).collect(Collectors.toList());

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMMM");
            Map<String, List<OrderResponse>> groupedOrderResponseMap = orderResponseList.stream().collect(Collectors.groupingBy(order -> simpleDateFormat.format(order.getCreated())));

            ArrayList<MonthlyStatisticsResponse> monthlyStatisticsResponseArrayList = new ArrayList<>();
            for(Map.Entry<String, List<OrderResponse>> entry : groupedOrderResponseMap.entrySet()){
                String month = entry.getKey();
                List<OrderResponse> monthlyOrderResponseList = entry.getValue();
                Long monthlyTotalOrderCount = monthlyOrderResponseList.stream().count();
                Integer monthlyTotalBookCount = monthlyOrderResponseList.stream().map(OrderResponse::getTotalBookCount).reduce(0, Integer::sum);
                BigDecimal monthlyTotalPurchasedAmount = monthlyOrderResponseList.stream().map(OrderResponse::getTotalPurchasedAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
                monthlyStatisticsResponseArrayList.add(MonthlyStatisticsResponse.builder()
                        .month(month)
                        .totalOrderCount(monthlyTotalOrderCount)
                        .totalBookCount(monthlyTotalBookCount)
                        .totalPurchasedAmount(monthlyTotalPurchasedAmount).build());
            }

            LOGGER.info("Monthly statistics created");
            return monthlyStatisticsResponseArrayList;
        } catch (OrderNotFoundException orderNotFoundException){
            LOGGER.error("Order not found for monthly statistics");
            throw new OrderNotFoundException();
        } catch (Exception e){
            throw new InternalServerError(DateUtil.getNow(), e);
        }
    }
}
