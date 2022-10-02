package com.project.readingisgood.repository;

import com.project.readingisgood.entity.Book;
import com.project.readingisgood.entity.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PriceRepository extends JpaRepository<Price, Long> {

    @Query("SELECT p FROM Price p WHERE p.book = :book")
    Optional<Price> findByBook(Book book);

}
