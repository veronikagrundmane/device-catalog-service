package com.smartdevicestore.orderservice.infrastructure.persistence.repository;

import com.smartdevicestore.orderservice.domain.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpringDataOrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByCustomerId(String customerId);
}
