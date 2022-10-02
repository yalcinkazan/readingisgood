package com.project.readingisgood.service;

import com.project.readingisgood.dto.request.BookCreationRequest;
import com.project.readingisgood.dto.response.BookInfoResponse;
import com.project.readingisgood.dto.response.BookStockExpandResponse;
import com.project.readingisgood.entity.Book;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface BookService {

    BookInfoResponse saveBook(BookCreationRequest bookRequest);
    BookStockExpandResponse addStock(Long id, Integer quantity);
    BookInfoResponse getBookInfo(Long id);
    Optional<Book> findBookById(Long id);

}
