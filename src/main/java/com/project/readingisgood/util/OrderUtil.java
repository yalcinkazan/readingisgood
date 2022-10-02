package com.project.readingisgood.util;

import com.project.readingisgood.dto.response.OrderDetailResponse;
import com.project.readingisgood.dto.response.OrderResponse;
import com.project.readingisgood.entity.Orders;

import java.math.BigDecimal;
import java.util.List;

public class OrderUtil {

    private OrderUtil() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Prepare order response with order and order detail
     *
     * @param order
     * @param orderDetailResponseList
     * @return
     */
    public static OrderResponse prepareOrderResponse(Orders order, List<OrderDetailResponse> orderDetailResponseList){
        OrderResponse orderResponse = new OrderResponse();
        BigDecimal totalAmount = orderDetailResponseList.stream().map(OrderDetailResponse::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
        Integer totalQuantity = orderDetailResponseList.stream().map(OrderDetailResponse::getQuantity).reduce(0, Integer::sum);
        orderResponse.setId(order.getId());
        orderResponse.setCreated(order.getCreated());
        orderResponse.setCustomer(order.getCustomer().getId());
        orderResponse.setStatus(order.getStatus());
        orderResponse.setOrderContent(orderDetailResponseList);
        orderResponse.setTotalPurchasedAmount(totalAmount);
        orderResponse.setTotalBookCount(totalQuantity);
        return orderResponse;
    }

}
