package com.project.readingisgood.repository;

import com.project.readingisgood.entity.Book;
import com.project.readingisgood.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {

    @Query("SELECT s FROM Stock s WHERE s.book = :book")
    Optional<Stock> findByBook(Book book);

}
