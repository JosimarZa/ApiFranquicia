package com.ppiprueba.apirestprueba.repository;

import com.ppiprueba.apirestprueba.model.Sucursal;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SucursalRepository extends MongoRepository<Sucursal, String> {
    
}
