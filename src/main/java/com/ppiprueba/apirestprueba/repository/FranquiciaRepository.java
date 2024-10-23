package com.ppiprueba.apirestprueba.repository;

import com.ppiprueba.apirestprueba.model.Franquicia;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FranquiciaRepository extends MongoRepository<Franquicia, String> {
    // Puedes agregar consultas personalizadas si es necesario
}