package com.scalyz.scalyz.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "service_id")
public class ServiceIdEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String value;

    public ServiceIdEntity() {}

    public ServiceIdEntity(String value) {
        this.value = value;
    }

    public Long getId() { return id; }
    public String getValue() { return value; }
    public void setValue(String value) { this.value = value; }
}
