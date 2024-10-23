package com.ppiprueba.apirestprueba.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
@Document(collection = "franquicias")
public class Franquicia {
    @Id
    private String id;
    private String nombre;
    private List<Sucursal> sucursales;

    public Franquicia() {
        this.sucursales = new ArrayList<>(); 
    }
}
