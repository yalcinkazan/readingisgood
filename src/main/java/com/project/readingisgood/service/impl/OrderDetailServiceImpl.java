package com.project.readingisgood.service.impl;

import com.project.readingisgood.dto.request.OrderDetailRequest;
import com.project.readingisgood.dto.response.OrderDetailResponse;
import com.project.readingisgood.entity.Book;
import com.project.readingisgood.entity.Orders;
import com.project.readingisgood.entity.OrdersDetail;
import com.project.readingisgood.entity.Price;
import com.project.readingisgood.exception.InternalServerError;
import com.project.readingisgood.repository.OrderDetailRepository;
import com.project.readingisgood.service.AbstractService;
import com.project.readingisgood.service.BookService;
import com.project.readingisgood.service.OrderDetailService;
import com.project.readingisgood.service.PriceService;
import com.project.readingisgood.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderDetailServiceImpl extends AbstractService implements OrderDetailService {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private BookService bookService;

    @Autowired
    private PriceService priceService;

    @Override
    @Transactional
    public List<OrderDetailResponse> createOrderDetail(Orders order, ArrayList<OrderDetailRequest> orderDetailRequests) {
        try {
            return orderDetailRequests.stream().map(content ->{
                Optional<Book> book = this.bookService.findBookById(content.getBook());
                Optional<Price> price = this.priceService.findByBook(book.get());
                BigDecimal total = price.get().getAmount().multiply(BigDecimal.valueOf(content.getQuantity()));
                this.orderDetailRepository.save(OrdersDetail.builder()
                        .orders(order)
                        .book(book.get())
                        .quantity(content.getQuantity())
                        .total(total).build());
                return OrderDetailResponse.builder().book(content.getBook()).amount(total).quantity(content.getQuantity()).build();
            }).collect(Collectors.toList());
        } catch (Exception e){
            throw new InternalServerError(DateUtil.getNow(), e);
        }
    }

    @Override
    public List<OrdersDetail> findByOrder(Orders order) {
        return this.orderDetailRepository.findByOrder(order);
    }

}

