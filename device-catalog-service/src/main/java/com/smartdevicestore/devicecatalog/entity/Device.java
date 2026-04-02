package com.smartdevicestore.devicecatalog.entity;

import jakarta.persistence.*;

@Entity
public class Device {

    // Unique identifier for the device
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Device name (e.g. WiFi Modem AX3000)
    private String name;

    // Device type (e.g. Modem, Router, Extender)
    private String type;

    // Brand or manufacturer
    private String brand;

    // Device price
    private Double price;

    // Default constructor required by JPA
    public Device() {}

    // Getters and setters

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