package com.project.readingisgood.service;

import com.project.readingisgood.dto.request.OrderCreationRequest;
import com.project.readingisgood.dto.request.OrderDateRangeRequest;
import com.project.readingisgood.dto.response.OrderResponse;
import com.project.readingisgood.entity.Customer;
import com.project.readingisgood.entity.Orders;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface OrderService {
    OrderResponse createOrder(OrderCreationRequest orderCreationRequest);
    OrderResponse getOrderById(Long orderId);
    Optional<List<Orders>> findByCustomer(Customer customer, Pageable pageable);
    List<OrderResponse> findByDateRange(OrderDateRangeRequest orderDateRangeRequest, int pageNo, int pageSize);
    Optional<List<Orders>> findAll();
}
