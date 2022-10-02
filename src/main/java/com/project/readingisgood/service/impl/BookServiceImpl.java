package com.project.readingisgood.service.impl;

import com.project.readingisgood.dto.request.BookCreationRequest;
import com.project.readingisgood.dto.response.BookInfoResponse;
import com.project.readingisgood.dto.response.BookStockExpandResponse;
import com.project.readingisgood.entity.Book;
import com.project.readingisgood.entity.Price;
import com.project.readingisgood.entity.Stock;
import com.project.readingisgood.exception.BookNotFoundException;
import com.project.readingisgood.exception.InternalServerError;
import com.project.readingisgood.exception.PriceNotFoundException;
import com.project.readingisgood.exception.QuantityBadRequestException;
import com.project.readingisgood.exception.StockNotFoundException;
import com.project.readingisgood.repository.BookRepository;
import com.project.readingisgood.service.AbstractService;
import com.project.readingisgood.service.BookService;
import com.project.readingisgood.service.PriceService;
import com.project.readingisgood.service.StockService;
import com.project.readingisgood.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Service
public class BookServiceImpl extends AbstractService implements BookService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BookServiceImpl.class);

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private StockService stockService;

    @Autowired
    private PriceService priceService;

    @Override
    @Transactional
    public BookInfoResponse saveBook(BookCreationRequest bookRequest) {
        try {
            Book book = this.mapper.map(bookRequest, Book.class);
            this.bookRepository.save(book);

            this.stockService.saveStock(book, 0);
            this.priceService.savePrice(book, bookRequest.getPrice());

            BookInfoResponse bookCreationResponse = this.mapper.map(book, BookInfoResponse.class);
            bookCreationResponse.setPrice(bookRequest.getPrice());
            bookCreationResponse.setQuantity(0);
            LOGGER.info("Book created with id {}", book.getId());
            return bookCreationResponse;
        } catch (Exception e){
            throw new InternalServerError(DateUtil.getNow(), e);
        }
    }

    @Override
    @Transactional
    public BookStockExpandResponse addStock(Long id, Integer quantity) {
        try {
            if(0 >= quantity) throw new QuantityBadRequestException(id);
            Optional<Book> book = this.bookRepository.findById(id);
            if(!book.isPresent()) throw new BookNotFoundException(id);
            Optional<Stock> stock = this.stockService.findByBook(book.get());
            if(!stock.isPresent()) throw new StockNotFoundException();
            stock.get().setQuantity(stock.get().getQuantity() + quantity);
            this.stockService.updateStock(stock.get());

            LOGGER.info("Book stock expanded id {}", book.get().getId());
            return BookStockExpandResponse.builder()
                    .id(book.get().getId())
                    .quantity(stock.get().getQuantity())
                    .message("Stock Updated")
                    .time(DateUtil.getNow()).build();

        } catch (QuantityBadRequestException quantityBadRequestException){
            LOGGER.error("Quantity have to be more than 0");
            throw new QuantityBadRequestException(id);
        }catch (BookNotFoundException bookNotFoundException){
            LOGGER.error("Book not found with id {}", id);
            throw new BookNotFoundException(id);
        } catch (StockNotFoundException stockNotFoundException){
            LOGGER.error("Stock not found");
            throw new StockNotFoundException();
        } catch (Exception e){
            throw new InternalServerError(DateUtil.getNow(), e);
        }

    }

    @Override
    public BookInfoResponse getBookInfo(Long id) {
        try {
            Optional<Book> book = this.bookRepository.findById(id);
            if(!book.isPresent()) throw new BookNotFoundException(id);
            Optional<Stock> stock = this.stockService.findByBook(book.get());
            if(!stock.isPresent()) throw new StockNotFoundException();
            BookInfoResponse bookInfoResponse = this.mapper.map(book.get(), BookInfoResponse.class);
            bookInfoResponse.setQuantity(stock.get().getQuantity());
            Optional<Price> price = this.priceService.findByBook(book.get());
            if(!price.isPresent()) throw new PriceNotFoundException();
            bookInfoResponse.setPrice(price.get().getAmount());
            return bookInfoResponse;
        } catch (BookNotFoundException bookNotFoundException){
            LOGGER.error("Book not found with id {}", id);
            throw new BookNotFoundException(id);
        } catch (StockNotFoundException stockNotFoundException){
            LOGGER.error("Stock not found");
            throw new StockNotFoundException();
        } catch (PriceNotFoundException priceNotFoundException){
            LOGGER.error("Price not found");
            throw new PriceNotFoundException();
        } catch (Exception e){
            throw new InternalServerError(DateUtil.getNow(), e);
        }
    }

    @Override
    public Optional<Book> findBookById(Long id) {
        return this.bookRepository.findBookById(id);
    }

}
