package com.project.readingisgood.service;

import com.project.readingisgood.entity.Book;
import com.project.readingisgood.entity.Price;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public interface PriceService {

    Optional<Price> findByBook(Book book);
    Optional<Price> savePrice(Price price);
    Optional<Price> savePrice(Book book, BigDecimal amount);

}
