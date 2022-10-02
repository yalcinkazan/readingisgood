package com.project.readingisgood.repository;

import com.project.readingisgood.entity.Customer;
import com.project.readingisgood.entity.Orders;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Long> {

    @Query("SELECT o FROM Orders o WHERE o.customer = :customer")
    Optional<List<Orders>> findByCustomer(Customer customer, Pageable pageable);

    @Query("SELECT o FROM Orders o WHERE o.created BETWEEN :startDate AND :endDate")
    Optional<List<Orders>> findByDateRange(Date startDate, Date endDate, Pageable pageable);

}
