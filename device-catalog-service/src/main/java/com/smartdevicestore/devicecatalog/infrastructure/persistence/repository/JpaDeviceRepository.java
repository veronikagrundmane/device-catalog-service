package com.smartdevicestore.devicecatalog.infrastructure.persistence.repository;

import com.smartdevicestore.devicecatalog.domain.model.Device;
import com.smartdevicestore.devicecatalog.domain.repository.DeviceRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class JpaDeviceRepository implements DeviceRepository {

    private final SpringDataDeviceRepository springDataDeviceRepository;

    public JpaDeviceRepository(SpringDataDeviceRepository springDataDeviceRepository) {
        this.springDataDeviceRepository = springDataDeviceRepository;
    }

    @Override
    public List<Device> findAll() {
        return springDataDeviceRepository.findAll();
    }

    @Override
    public Optional<Device> findById(Long id) {
        return springDataDeviceRepository.findById(id);
    }

    @Override
    public Device save(Device device) {
        return springDataDeviceRepository.save(device);
    }
}