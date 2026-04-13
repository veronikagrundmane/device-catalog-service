package com.smartdevicestore.orderservice.domain.exception;

public class DeviceNotFoundException extends RuntimeException {

    public DeviceNotFoundException(Long deviceId) {
        super("Device not found or unavailable: " + deviceId);
    }
}
