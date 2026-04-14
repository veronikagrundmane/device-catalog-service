package com.smartdevicestore.orderservice.web.dto;

public class OrderItemRequest {

    private Long deviceId;
    private Integer quantity;

    public OrderItemRequest() {}

    public Long getDeviceId() { return deviceId; }
    public void setDeviceId(Long deviceId) { this.deviceId = deviceId; }

    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
}
