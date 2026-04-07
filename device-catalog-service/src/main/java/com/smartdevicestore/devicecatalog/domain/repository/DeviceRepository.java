package com.smartdevicestore.devicecatalog.domain.repository;

import com.smartdevicestore.devicecatalog.domain.model.Device;

import java.util.List;
import java.util.Optional;

public interface DeviceRepository {

    // Return all devices
    List<Device> findAll();

    // Return one device by id
    Optional<Device> findById(Long id);

    // Save a new device
    Device save(Device device);
}