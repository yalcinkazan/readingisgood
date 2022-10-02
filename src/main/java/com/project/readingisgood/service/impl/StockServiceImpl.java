package com.project.readingisgood.service.impl;

import com.project.readingisgood.dto.request.OrderDetailRequest;
import com.project.readingisgood.entity.Book;
import com.project.readingisgood.entity.Stock;
import com.project.readingisgood.exception.BookNotFoundException;
import com.project.readingisgood.exception.InternalServerError;
import com.project.readingisgood.exception.OrderBadRequestException;
import com.project.readingisgood.exception.OrderDetailBadRequestException;
import com.project.readingisgood.exception.QuantityBadRequestException;
import com.project.readingisgood.exception.StockBadRequestException;
import com.project.readingisgood.repository.StockRepository;
import com.project.readingisgood.service.AbstractService;
import com.project.readingisgood.service.BookService;
import com.project.readingisgood.service.StockService;
import com.project.readingisgood.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class StockServiceImpl extends AbstractService implements StockService {

    private static final Logger LOGGER = LoggerFactory.getLogger(StockServiceImpl.class);

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private BookService bookService;

    @Override
    public Optional<Stock> findByBook(Book book) {
        return this.stockRepository.findByBook(book);
    }

    @Override
    public Optional<Stock> saveStock(Stock stock) {
        return Optional.of(this.stockRepository.save(stock));
    }

    @Override
    @Transactional
    public Optional<Stock> saveStock(Book book, Integer quantity) {
        return this.saveStock(Stock.builder().book(book).quantity(quantity).build());
    }

    @Override
    public void updateStock(Stock stock) {
        this.stockRepository.save(stock);
    }

    /**
     * Checks whether the book quantities in the order details are enough
     *
     * isolation = Isolation.REPEATABLE_READ
     * Repeatable read will ensure that if two concurrent transactions read and change the same record more or less concurrently, only one of them will succeed.
     *
     * @param orderDetailRequests
     */
    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void checkStock(ArrayList<OrderDetailRequest> orderDetailRequests) {
        if(orderDetailRequests == null || orderDetailRequests.isEmpty()){
            LOGGER.error("Empty order detail");
            throw new OrderDetailBadRequestException();
        }
        orderDetailRequests.forEach(content -> {
            if(content.getBook() == null || content.getQuantity() == null){
                LOGGER.error("Empty quantity");
                throw new OrderBadRequestException();
            }
            if(content.getQuantity() < 1){
                LOGGER.error("Quantity have to be more than 0 for book id {}", content.getBook());
                throw new QuantityBadRequestException(content.getBook());
            }
            Optional<Book> book = this.bookService.findBookById(content.getBook());
            if(!book.isPresent()){
                LOGGER.error("Book not found id {}", content.getBook());
                throw new BookNotFoundException(content.getBook());
            }
            Optional<Stock> stock = this.findByBook(book.get());
            if(!stock.isPresent()){
                LOGGER.error("There is no stock entry for book id {}", content.getBook());
                throw new StockBadRequestException(content.getBook());
            }
            if(stock.get().getQuantity() < content.getQuantity()){
                LOGGER.error("Not enough stock for book id {}", content.getBook());
                throw new StockBadRequestException(content.getBook());
            }
        });
    }


    /**
     * Stock reduction operation
     *
     * isolation = Isolation.REPEATABLE_READ
     * Repeatable read will ensure that if two concurrent transactions read and change the same record more or less concurrently, only one of them will succeed.
     *
     * @param orderDetailRequests
     */
    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void stockReduction(ArrayList<OrderDetailRequest> orderDetailRequests) {
        try {
            orderDetailRequests.forEach(content -> {
                Optional<Book> book = this.bookService.findBookById(content.getBook());
                Optional<Stock> stock = this.findByBook(book.get());
                stock.get().setQuantity(stock.get().getQuantity() - content.getQuantity());
                if(stock.get().getQuantity() < 0) throw new StockBadRequestException(content.getBook());
                this.updateStock(stock.get());
            });
        } catch (StockBadRequestException stockBadRequestException){
            LOGGER.error("Not enough stock");
            throw new StockBadRequestException(stockBadRequestException.getBookId());
        }catch (Exception e){
            throw new InternalServerError(DateUtil.getNow(), e);
        }
    }

}
