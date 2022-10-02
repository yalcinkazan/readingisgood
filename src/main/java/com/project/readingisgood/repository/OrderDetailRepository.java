package com.project.readingisgood.repository;

import com.project.readingisgood.entity.Orders;
import com.project.readingisgood.entity.OrdersDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrdersDetail, Long> {

    @Query("SELECT od FROM OrdersDetail od WHERE od.orders = :order")
    List<OrdersDetail> findByOrder(Orders order);

}
