package com.project.readingisgood.service;

import com.project.readingisgood.dto.request.OrderDetailRequest;
import com.project.readingisgood.entity.Book;
import com.project.readingisgood.entity.Stock;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public interface StockService {

    Optional<Stock> findByBook(Book book);
    Optional<Stock> saveStock(Stock stock);
    Optional<Stock> saveStock(Book book, Integer quantity);
    void updateStock(Stock stock);
    void checkStock(ArrayList<OrderDetailRequest> orderDetailRequests);
    void stockReduction(ArrayList<OrderDetailRequest> orderDetailRequests);

}
