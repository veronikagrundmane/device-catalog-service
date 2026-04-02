package com.smartdevicestore.devicecatalog.controller;

import com.smartdevicestore.devicecatalog.entity.Device;
import com.smartdevicestore.devicecatalog.service.DeviceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/devices")
public class DeviceController {

    // Service dependency for device operations
    private final DeviceService service;

    public DeviceController(DeviceService service) {
        this.service = service;
    }

    // Get all devices
    @GetMapping
    public List<Device> getAllDevices() {
        return service.getAllDevices();
    }

    // Get device by id
    @GetMapping("/{id}")
    public ResponseEntity<Device> getDeviceById(@PathVariable Long id) {
        return service.getDeviceById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Create a new device
    @PostMapping
    public Device createDevice(@RequestBody Device device) {
        return service.createDevice(device);
    }
}