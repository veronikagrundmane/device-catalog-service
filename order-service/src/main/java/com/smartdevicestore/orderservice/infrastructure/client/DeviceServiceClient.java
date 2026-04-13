package com.smartdevicestore.orderservice.infrastructure.client;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.Optional;

@Component
public class DeviceServiceClient {

    private final RestClient restClient;

    public DeviceServiceClient(RestClient restClient) {
        this.restClient = restClient;
    }

    public Optional<DeviceInfo> getDeviceInfo(Long deviceId) {
        DeviceInfo info = restClient.get()
                .uri("/api/devices/{id}", deviceId)
                .retrieve()
                .body(DeviceInfo.class);
        return Optional.ofNullable(info);
    }
}
