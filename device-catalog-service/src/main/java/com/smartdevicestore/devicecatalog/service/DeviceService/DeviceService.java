package com.smartdevicestore.devicecatalog.service;

import com.smartdevicestore.devicecatalog.entity.Device;
import com.smartdevicestore.devicecatalog.repository.DeviceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeviceService {

    // Repository dependency for database operations
    private final DeviceRepository repository;

    public DeviceService(DeviceRepository repository) {
        this.repository = repository;
    }

    // Return all devices
    public List<Device> getAllDevices() {
        return repository.findAll();
    }

    // Return one device by id
    public Optional<Device> getDeviceById(Long id) {
        return repository.findById(id);
    }

    // Save a new device
    public Device createDevice(Device device) {
        return repository.save(device);
    }
}