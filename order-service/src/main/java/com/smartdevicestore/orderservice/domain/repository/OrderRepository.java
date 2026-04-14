package com.smartdevicestore.orderservice.domain.repository;

import com.smartdevicestore.orderservice.domain.model.Order;

import java.util.List;
import java.util.Optional;

public interface OrderRepository {

    List<Order> findAll();

    Optional<Order> findById(Long id);

    List<Order> findByCustomerId(String customerId);

    Order save(Order order);

    void deleteById(Long id);

    boolean existsById(Long id);
}
