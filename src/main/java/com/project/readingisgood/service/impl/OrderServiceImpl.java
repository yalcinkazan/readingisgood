package com.project.readingisgood.service.impl;

import com.project.readingisgood.dto.request.OrderCreationRequest;
import com.project.readingisgood.dto.request.OrderDateRangeRequest;
import com.project.readingisgood.dto.response.OrderDetailResponse;
import com.project.readingisgood.dto.response.OrderResponse;
import com.project.readingisgood.entity.Customer;
import com.project.readingisgood.entity.Orders;
import com.project.readingisgood.entity.OrdersDetail;
import com.project.readingisgood.enums.OrderStatus;
import com.project.readingisgood.exception.CustomerNotFoundException;
import com.project.readingisgood.exception.InternalServerError;
import com.project.readingisgood.exception.OrderNotFoundException;
import com.project.readingisgood.repository.OrderRepository;
import com.project.readingisgood.service.AbstractService;
import com.project.readingisgood.service.CustomerService;
import com.project.readingisgood.service.OrderDetailService;
import com.project.readingisgood.service.OrderService;
import com.project.readingisgood.service.StockService;
import com.project.readingisgood.util.DateUtil;
import com.project.readingisgood.util.OrderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl extends AbstractService implements OrderService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDetailService orderDetailService;

    @Autowired
    private StockService stockService;

    @Autowired
    private CustomerService customerService;

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public OrderResponse createOrder(OrderCreationRequest orderCreationRequest) {
        this.stockService.checkStock(orderCreationRequest.getOrderContent());
        this.stockService.stockReduction(orderCreationRequest.getOrderContent());
        try {
            Optional<Customer> customer = this.customerService.findById(orderCreationRequest.getCustomer());
            if(!customer.isPresent()) throw new CustomerNotFoundException(orderCreationRequest.getCustomer());
            Orders order = this.orderRepository.save(Orders.builder().customer(customer.get()).status(OrderStatus.ORDER_CREATED).build());
            List<OrderDetailResponse> orderDetailResponseList = this.orderDetailService.createOrderDetail(order, orderCreationRequest.getOrderContent());
            LOGGER.info("Order created with id {}", order.getId());
            return OrderUtil.prepareOrderResponse(order, orderDetailResponseList);
        } catch (CustomerNotFoundException customerNotFoundException){
            LOGGER.error("Customer not found with id {}", orderCreationRequest.getCustomer());
            throw new CustomerNotFoundException(orderCreationRequest.getCustomer());
        } catch (Exception e){
            throw new InternalServerError(DateUtil.getNow(), e);
        }
    }

    @Override
    public OrderResponse getOrderById(Long orderId) {
        try {
            Optional<Orders> order = this.orderRepository.findById(orderId);
            if(!order.isPresent()) throw new OrderNotFoundException();
            List<OrdersDetail> ordersDetailList =  this.orderDetailService.findByOrder(order.get());
            List<OrderDetailResponse> orderDetailResponseList = ordersDetailList.stream().map(detail ->
                            OrderDetailResponse.builder()
                                    .book(detail.getBook().getId())
                                    .amount(detail.getTotal())
                                    .quantity(detail.getQuantity()).build())
                    .collect(Collectors.toList());
            return OrderUtil.prepareOrderResponse(order.get(), orderDetailResponseList);
        } catch (OrderNotFoundException orderNotFoundException){
            LOGGER.error("Order not found with id {}", orderId);
            throw new OrderNotFoundException();
        } catch (Exception e){
            throw new InternalServerError(DateUtil.getNow(), e);
        }
    }

    @Override
    public Optional<List<Orders>> findByCustomer(Customer customer, Pageable pageable) {
        return this.orderRepository.findByCustomer(customer, pageable);
    }

    @Override
    public List<OrderResponse> findByDateRange(OrderDateRangeRequest orderDateRangeRequest, int pageNo, int pageSize) {
        try {
            Pageable pageable = PageRequest.of(pageNo, pageSize);
            Optional<List<Orders>> orderListOptional = this.orderRepository.findByDateRange(orderDateRangeRequest.getStartDate(), orderDateRangeRequest.getEndDate(), pageable);
            if(!orderListOptional.isPresent() || orderListOptional.get().isEmpty()) throw new OrderNotFoundException();
            return orderListOptional.get().stream().map(order ->
                    this.getOrderById(order.getId())).collect(Collectors.toList());
        } catch (OrderNotFoundException orderNotFoundException){
            LOGGER.error("Orders not found in date range");
            throw new OrderNotFoundException();
        } catch (Exception e){
            throw new InternalServerError(DateUtil.getNow(), e);
        }
    }

    @Override
    public Optional<List<Orders>> findAll() {
        return Optional.of(this.orderRepository.findAll());
    }

}
