package com.smartdevicestore.devicecatalog.web.controller;

import com.smartdevicestore.devicecatalog.application.service.DeviceApplicationService;
import com.smartdevicestore.devicecatalog.domain.model.Device;
import com.smartdevicestore.devicecatalog.web.dto.CreateDeviceRequest;
import com.smartdevicestore.devicecatalog.web.dto.DeviceResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/devices")
public class DeviceController {

    private final DeviceApplicationService deviceApplicationService;

    public DeviceController(DeviceApplicationService deviceApplicationService) {
        this.deviceApplicationService = deviceApplicationService;
    }

    @GetMapping
    public List<DeviceResponse> getAllDevices() {
        return deviceApplicationService.getAllDevices()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DeviceResponse> getDeviceById(@PathVariable Long id) {
        return deviceApplicationService.getDeviceById(id)
                .map(device -> ResponseEntity.ok(toResponse(device)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public DeviceResponse createDevice(@RequestBody CreateDeviceRequest request) {
        Device createdDevice = deviceApplicationService.createDevice(
                request.getName(),
                request.getType(),
                request.getBrand(),
                request.getPrice()
        );

        return toResponse(createdDevice);
    }

    private DeviceResponse toResponse(Device device) {
        return new DeviceResponse(
                device.getId(),
                device.getName(),
                device.getType(),
                device.getBrand(),
                device.getPrice()
        );
    }
}