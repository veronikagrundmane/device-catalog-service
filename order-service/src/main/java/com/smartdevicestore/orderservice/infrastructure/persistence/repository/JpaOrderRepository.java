package com.smartdevicestore.orderservice.infrastructure.persistence.repository;

import com.smartdevicestore.orderservice.domain.model.Order;
import com.smartdevicestore.orderservice.domain.repository.OrderRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class JpaOrderRepository implements OrderRepository {

    private final SpringDataOrderRepository springDataOrderRepository;

    public JpaOrderRepository(SpringDataOrderRepository springDataOrderRepository) {
        this.springDataOrderRepository = springDataOrderRepository;
    }

    @Override
    public List<Order> findAll() {
        return springDataOrderRepository.findAll();
    }

    @Override
    public Optional<Order> findById(Long id) {
        return springDataOrderRepository.findById(id);
    }

    @Override
    public List<Order> findByCustomerId(String customerId) {
        return springDataOrderRepository.findByCustomerId(customerId);
    }

    @Override
    public Order save(Order order) {
        return springDataOrderRepository.save(order);
    }

    @Override
    public void deleteById(Long id) {
        springDataOrderRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return springDataOrderRepository.existsById(id);
    }
}
