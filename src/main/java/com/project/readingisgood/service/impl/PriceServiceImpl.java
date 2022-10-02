package com.project.readingisgood.service.impl;

import com.project.readingisgood.entity.Book;
import com.project.readingisgood.entity.Price;
import com.project.readingisgood.repository.PriceRepository;
import com.project.readingisgood.service.AbstractService;
import com.project.readingisgood.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.util.Optional;

@Service
public class PriceServiceImpl extends AbstractService implements PriceService {

    @Autowired
    private PriceRepository priceRepository;

    @Override
    public Optional<Price> findByBook(Book book) {
        return this.priceRepository.findByBook(book);
    }

    @Override
    public Optional<Price> savePrice(Price price) {
        return Optional.of(this.priceRepository.save(price));
    }

    @Override
    @Transactional
    public Optional<Price> savePrice(Book book, BigDecimal amount) {
        return this.savePrice(Price.builder().book(book).amount(amount).build());
    }
}
