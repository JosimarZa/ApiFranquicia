package com.ppiprueba.apirestprueba.model;

import java.util.UUID;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class Producto {
    @Id
    private String id;
    private String nombre;
    private int stock;

    
    public Producto() {
        this.id = UUID.randomUUID().toString();
    }
}
