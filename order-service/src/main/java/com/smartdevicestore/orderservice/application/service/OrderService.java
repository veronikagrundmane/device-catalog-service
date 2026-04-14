package com.smartdevicestore.orderservice.application.service;

import com.smartdevicestore.orderservice.domain.exception.DeviceNotFoundException;
import com.smartdevicestore.orderservice.domain.exception.OrderNotFoundException;
import com.smartdevicestore.orderservice.domain.model.*;
import com.smartdevicestore.orderservice.domain.repository.OrderRepository;
import com.smartdevicestore.orderservice.infrastructure.client.DeviceInfo;
import com.smartdevicestore.orderservice.infrastructure.client.DeviceServiceClient;
import com.smartdevicestore.orderservice.web.dto.OrderItemRequest;
import com.smartdevicestore.orderservice.web.dto.OrderRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;
    private final DeviceServiceClient deviceServiceClient;

    public OrderService(OrderRepository orderRepository, DeviceServiceClient deviceServiceClient) {
        this.orderRepository = orderRepository;
        this.deviceServiceClient = deviceServiceClient;
    }

    public Order createOrder(OrderRequest request) {
        if (request.getItems() == null || request.getItems().isEmpty()) {
            throw new IllegalArgumentException("Order must contain at least one item");
        }

        Order order = new Order(request.getCustomerId());
        BigDecimal total = BigDecimal.ZERO;

        for (OrderItemRequest itemReq : request.getItems()) {
            DeviceInfo device = deviceServiceClient.getDeviceInfo(itemReq.getDeviceId())
                    .orElseThrow(() -> new DeviceNotFoundException(itemReq.getDeviceId()));

            BigDecimal itemPrice = BigDecimal.valueOf(device.getPrice());
            order.getItems().add(new OrderItem(order, device.getId(), device.getName(),
                    itemPrice, itemReq.getQuantity()));
            total = total.add(itemPrice.multiply(BigDecimal.valueOf(itemReq.getQuantity())));
        }

        order.setTotalAmount(total);
        return orderRepository.save(order);
    }

    @Transactional(readOnly = true)
    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(id));
    }

    @Transactional(readOnly = true)
    public List<Order> getOrdersByCustomerId(String customerId) {
        return orderRepository.findByCustomerId(customerId);
    }

    public Order updateOrderStatus(Long id, OrderStatus newStatus) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(id));
        order.setStatus(newStatus);
        return orderRepository.save(order);
    }

    public void cancelOrder(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(id));
        order.setStatus(OrderStatus.CANCELLED);
        orderRepository.save(order);
    }
}
