package com.smartdevicestore.orderservice.web.dto;

import java.math.BigDecimal;

public class OrderItemResponse {

    private Long id;
    private Long deviceId;
    private String deviceName;
    private BigDecimal price;
    private Integer quantity;

    public OrderItemResponse() {}

    public OrderItemResponse(Long id, Long deviceId, String deviceName, BigDecimal price, Integer quantity) {
        this.id = id;
        this.deviceId = deviceId;
        this.deviceName = deviceName;
        this.price = price;
        this.quantity = quantity;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getDeviceId() { return deviceId; }
    public void setDeviceId(Long deviceId) { this.deviceId = deviceId; }

    public String getDeviceName() { return deviceName; }
    public void setDeviceName(String deviceName) { this.deviceName = deviceName; }

    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }

    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
}
