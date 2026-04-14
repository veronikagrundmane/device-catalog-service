package com.smartdevicestore.orderservice.infrastructure.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {

    @Value("${device.service.base-url}")
    private String deviceServiceBaseUrl;

    @Bean
    public RestClient deviceServiceRestClient() {
        return RestClient.builder()
                .baseUrl(deviceServiceBaseUrl)
                .build();
    }
}
