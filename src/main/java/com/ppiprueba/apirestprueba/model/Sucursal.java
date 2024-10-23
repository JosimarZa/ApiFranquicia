package com.ppiprueba.apirestprueba.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data 
public class Sucursal {
     @Id
    private String id;
    private String nombre;
    private List<Producto> productos;

    public Sucursal() {
        this.productos = new ArrayList<>(); 
    }
    
}

