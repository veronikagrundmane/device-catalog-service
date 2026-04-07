package com.smartdevicestore.devicecatalog.application.service;

import com.smartdevicestore.devicecatalog.domain.model.Device;
import com.smartdevicestore.devicecatalog.domain.repository.DeviceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeviceApplicationService {

    // Repository dependency through domain abstraction
    private final DeviceRepository deviceRepository;

    public DeviceApplicationService(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    // Return all devices
    public List<Device> getAllDevices() {
        return deviceRepository.findAll();
    }

    // Return one device by id
    public Optional<Device> getDeviceById(Long id) {
        return deviceRepository.findById(id);
    }

    // Create a new device
    public Device createDevice(String name, String type, String brand, Double price) {
        Device device = new Device(name, type, brand, price);
        return deviceRepository.save(device);
    }
}