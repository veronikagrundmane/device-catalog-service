package com.smartdevicestore.devicecatalog.web.dto;

public class DeviceResponse {

    private Long id;
    private String name;
    private String type;
    private String brand;
    private Double price;

    public DeviceResponse() {
    }

    public DeviceResponse(Long id, String name, String type, String brand, Double price) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.brand = brand;
        this.price = price;
    }

    public Long getId() {
        return id;
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

    public void setId(Long id) {
        this.id = id;
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