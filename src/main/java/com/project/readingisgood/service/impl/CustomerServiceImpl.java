package com.project.readingisgood.service.impl;

import com.project.readingisgood.dto.request.CustomerRegisterRequest;
import com.project.readingisgood.dto.response.CustomerRegisterResponse;
import com.project.readingisgood.dto.response.OrderResponse;
import com.project.readingisgood.entity.Customer;
import com.project.readingisgood.entity.Orders;
import com.project.readingisgood.exception.CustomerNotFoundException;
import com.project.readingisgood.exception.EmailConflictException;
import com.project.readingisgood.exception.EmailValidationException;
import com.project.readingisgood.exception.InternalServerError;
import com.project.readingisgood.exception.OrderNotFoundException;
import com.project.readingisgood.repository.CustomerRepository;
import com.project.readingisgood.service.AbstractService;
import com.project.readingisgood.service.CustomerService;
import com.project.readingisgood.service.OrderService;
import com.project.readingisgood.util.DateUtil;
import com.project.readingisgood.util.EmailUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl extends AbstractService implements CustomerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerServiceImpl.class);

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private OrderService orderService;

    @Transactional
    @Override
    public CustomerRegisterResponse saveCustomer(CustomerRegisterRequest customerRegisterRequest) {
        try {
            if(!EmailUtil.isValid(customerRegisterRequest.getEmail())) throw new EmailValidationException(customerRegisterRequest.getEmail());
            Optional<Customer> customerOptional = this.findByEmail(customerRegisterRequest.getEmail());
            if(customerOptional.isPresent()) throw new EmailConflictException(customerRegisterRequest.getEmail());
            Customer customer = this.mapper.map(customerRegisterRequest, Customer.class);
            this.customerRepository.save(customer);

            LOGGER.info("Customer created with id {}", customer.getId());
            return this.mapper.map(customer, CustomerRegisterResponse.class);

        } catch (EmailValidationException emailValidationException){
            LOGGER.error("Email is not valid {}", customerRegisterRequest.getEmail());
            throw new EmailValidationException(customerRegisterRequest.getEmail());
        } catch (EmailConflictException emailConflictException){
            LOGGER.error("Email already in use {}", customerRegisterRequest.getEmail());
            throw new EmailConflictException(customerRegisterRequest.getEmail());
        } catch (Exception e){
            throw new InternalServerError(DateUtil.getNow(), e);
        }
    }

    @Override
    public Optional<Customer> findByEmail(String email) {
        return this.customerRepository.findByEmail(email);
    }

    @Override
    public Optional<Customer> findById(Long id) {
        return this.customerRepository.findById(id);
    }

    @Override
    public List<OrderResponse> getAllOrdersByCustomer(Long customerId, int pageNo, int pageSize) {
        try {
            Optional<Customer> customerOptional = this.findById(customerId);
            if (!customerOptional.isPresent()) throw new CustomerNotFoundException(customerId);
            Pageable pageable = PageRequest.of(pageNo, pageSize);
            Optional<List<Orders>> orderListOptional = this.orderService.findByCustomer(customerOptional.get(), pageable);
            if (!orderListOptional.isPresent() || orderListOptional.get().isEmpty()) throw new OrderNotFoundException();

            return orderListOptional.get().stream().map(order ->
                    this.orderService.getOrderById(order.getId())).collect(Collectors.toList());

        } catch (CustomerNotFoundException customerNotFoundException) {
            LOGGER.error("Customer not found with id {}", customerId);
            throw new CustomerNotFoundException(customerId);
        } catch (OrderNotFoundException orderNotFoundException) {
            LOGGER.error("Order not found");
            throw new OrderNotFoundException();
        } catch (Exception e) {
            throw new InternalServerError(DateUtil.getNow(), e);
        }
    }

}
