package com.smartdevicestore.devicecatalog.repository;

import com.smartdevicestore.devicecatalog.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;

// Repository for Device entity
// Provides basic CRUD operations automatically
public interface DeviceRepository extends JpaRepository<Device, Long> {
}