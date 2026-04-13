package com.smartdevicestore.orderservice.infrastructure.client;

public class DeviceInfo {

    private Long id;
    private String name;
    private String type;
    private String brand;
    private Double price;

    public DeviceInfo() {}

    public DeviceInfo(Long id, String name, String type, String brand, Double price) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.brand = brand;
        this.price = price;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }
}
