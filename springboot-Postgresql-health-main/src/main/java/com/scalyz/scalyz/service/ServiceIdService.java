package com.scalyz.scalyz.service;

import com.scalyz.scalyz.entity.ServiceIdEntity;
import com.scalyz.scalyz.repository.ServiceIdRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ServiceIdService {
    private final ServiceIdRepository repo;
    private String cachedId;

    public ServiceIdService(ServiceIdRepository repo) {
        this.repo = repo;
    }

    @PostConstruct
    public void init() {
        var all = repo.findAll();
        if (all.isEmpty()) {
            ServiceIdEntity entity = repo.save(new ServiceIdEntity(UUID.randomUUID().toString()));
            cachedId = entity.getValue();
        } else {
            cachedId = all.get(0).getValue();
        }
    }

    public String getServiceId() {
        return cachedId;
    }
}
