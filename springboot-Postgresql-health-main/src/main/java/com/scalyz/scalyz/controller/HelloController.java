package com.scalyz.scalyz.controller;

import com.scalyz.scalyz.service.ServiceIdService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    private final ServiceIdService serviceIdService;

    public HelloController(ServiceIdService serviceIdService) {
        this.serviceIdService = serviceIdService;
    }

    @GetMapping("/")
    public String hello() {
        return "Hello";
    }

    @GetMapping("/id")
    public String id() {
        return serviceIdService.getServiceId();
    }
}
