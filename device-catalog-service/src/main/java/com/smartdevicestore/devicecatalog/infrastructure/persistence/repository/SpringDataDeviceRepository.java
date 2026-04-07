package com.smartdevicestore.devicecatalog.infrastructure.persistence.repository;

import com.smartdevicestore.devicecatalog.domain.model.Device;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataDeviceRepository extends JpaRepository<Device, Long> {
}