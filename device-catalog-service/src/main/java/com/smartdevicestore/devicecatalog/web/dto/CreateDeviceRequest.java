package com.smartdevicestore.devicecatalog.web.dto;

public class CreateDeviceRequest {

    // Device name
    private String name;

    // Device type
    private String type;

    // Brand or manufacturer
    private String brand;

    // Device price
    private Double price;

    public CreateDeviceRequest() {
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getBrand() {
        return brand;
    }

    public Double getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}