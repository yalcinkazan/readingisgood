package com.project.readingisgood.service;

import com.project.readingisgood.dto.request.OrderDetailRequest;
import com.project.readingisgood.dto.response.OrderDetailResponse;
import com.project.readingisgood.entity.Orders;
import com.project.readingisgood.entity.OrdersDetail;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public interface OrderDetailService {

    List<OrderDetailResponse> createOrderDetail(Orders order, ArrayList<OrderDetailRequest> orderDetailRequest);
    List<OrdersDetail> findByOrder(Orders order);

}
