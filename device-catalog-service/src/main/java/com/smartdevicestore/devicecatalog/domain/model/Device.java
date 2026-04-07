package com.smartdevicestore.devicecatalog.domain.model;

import jakarta.persistence.*;

@Entity
public class Device {

    // Unique identifier for the device
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Device name
    private String name;

    // Device type
    private String type;

    // Brand or manufacturer
    private String brand;

    // Device price
    private Double price;

    // Default constructor required by JPA
    public Device() {
    }

    public Device(String name, String type, String brand, Double price) {
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