package com.project.readingisgood.service;

import com.project.readingisgood.dto.request.CustomerRegisterRequest;
import com.project.readingisgood.dto.response.CustomerRegisterResponse;
import com.project.readingisgood.dto.response.OrderResponse;
import com.project.readingisgood.entity.Customer;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface CustomerService {

    CustomerRegisterResponse saveCustomer(CustomerRegisterRequest customerRegisterRequest);
    Optional<Customer> findByEmail(String email);
    Optional<Customer> findById(Long id);
    List<OrderResponse> getAllOrdersByCustomer(Long customerId, int pageNo, int pageSize);

}
