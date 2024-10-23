package com.ppiprueba.apirestprueba.service;

import com.ppiprueba.apirestprueba.model.Franquicia;
import com.ppiprueba.apirestprueba.model.Sucursal;
import com.ppiprueba.apirestprueba.model.Producto;
import com.ppiprueba.apirestprueba.repository.FranquiciaRepository;
import com.ppiprueba.apirestprueba.repository.SucursalRepository; // Importa tu repositorio de sucursales
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FranquiciaServiceImpl implements FranquiciaService {

    private final FranquiciaRepository franquiciaRepository;

    @Autowired
    private SucursalRepository sucursalRepository; // Inyección del repositorio de sucursales

    public FranquiciaServiceImpl(FranquiciaRepository franquiciaRepository) {
        this.franquiciaRepository = franquiciaRepository;
    }

   
    @Override
    public List<Franquicia> obtenerTodasFranquicias() {
        return franquiciaRepository.findAll();
    }

    @Override
    public Optional<Franquicia> obtenerFranquiciaPorId(String id) {
        return franquiciaRepository.findById(id);
    }

    @Override
    public Franquicia crearFranquicia(Franquicia franquicia) {
    if (franquicia.getSucursales() != null && !franquicia.getSucursales().isEmpty()) {
        // Guarda cada sucursal antes de guardar la franquicia
        franquicia.getSucursales().forEach(sucursal -> {
            // Guardar la sucursal, esto generará un ID automáticamente
            sucursalRepository.save(sucursal);
        });
    }
    return franquiciaRepository.save(franquicia);
    }

    @Override
    public Franquicia modificarNombreFranquicia(String franquiciaId, String nuevoNombre) {
    Optional<Franquicia> franquiciaOpt = franquiciaRepository.findById(franquiciaId);
    if (franquiciaOpt.isPresent()) {
        Franquicia franquicia = franquiciaOpt.get();
        franquicia.setNombre(nuevoNombre); // Cambiar el nombre de la franquicia
        return franquiciaRepository.save(franquicia); // Guardar la franquicia actualizada
    } else {
        throw new RuntimeException("Franquicia no encontrada");
    }
}


    @Override
    public Franquicia agregarSucursal(String franquiciaId, Sucursal sucursal) {
        Optional<Franquicia> franquiciaOpt = franquiciaRepository.findById(franquiciaId);
        if (franquiciaOpt.isPresent()) {
            Franquicia franquicia = franquiciaOpt.get();
            franquicia.getSucursales().add(sucursal); // Agregar la sucursal a la lista
            sucursalRepository.save(sucursal); // Guardar la sucursal en la base de datos
            return franquiciaRepository.save(franquicia); // Guardar la franquicia actualizada
        } else {
            throw new RuntimeException("Franquicia no encontrada");
        }
    }

    @Override
    public Franquicia agregarProducto(String franquiciaId, String sucursalId, Producto producto) {
    Optional<Franquicia> franquiciaOpt = franquiciaRepository.findById(franquiciaId);
    if (franquiciaOpt.isPresent()) {
        Franquicia franquicia = franquiciaOpt.get();
        franquicia.getSucursales().forEach(sucursal -> {
            if (sucursal.getId().equals(sucursalId)) {
                sucursal.getProductos().add(producto); // Agregar el producto a la sucursal
            }
        });
        return franquiciaRepository.save(franquicia); // Guardar la franquicia actualizada
    } else {
        throw new RuntimeException("Franquicia o sucursal no encontrada");
    }
}


    @Override
    public Franquicia modificarProducto(String franquiciaId, String sucursalId, String productoId, Producto productoActualizado) {
        Optional<Franquicia> franquiciaOpt = franquiciaRepository.findById(franquiciaId);
        if (franquiciaOpt.isPresent()) {
            Franquicia franquicia = franquiciaOpt.get();
            franquicia.getSucursales().forEach(sucursal -> {
                if (sucursal.getId().equals(sucursalId)) {
                    sucursal.getProductos().forEach(producto -> {
                        if (producto.getId().equals(productoId)) {
                            producto.setNombre(productoActualizado.getNombre());
                            producto.setStock(productoActualizado.getStock());
                        }
                    });
                }
            });
            return franquiciaRepository.save(franquicia);
        } else {
            throw new RuntimeException("Franquicia o sucursal no encontrada");
        }
    }

    @Override
    public Franquicia eliminarProducto(String franquiciaId, String sucursalId, String productoId) {
        Optional<Franquicia> franquiciaOpt = franquiciaRepository.findById(franquiciaId);
        if (franquiciaOpt.isPresent()) {
            Franquicia franquicia = franquiciaOpt.get();
            franquicia.getSucursales().forEach(sucursal -> {
                if (sucursal.getId().equals(sucursalId)) {
                    sucursal.getProductos().removeIf(producto -> producto.getId().equals(productoId)); // Eliminar el producto
                }
            });
            return franquiciaRepository.save(franquicia);
        } else {
            throw new RuntimeException("Franquicia o sucursal no encontrada");
        }
    }

    @Override
    public Franquicia modificarStockProducto(String franquiciaId, String sucursalId, String productoId, int nuevoStock) {
        Optional<Franquicia> franquiciaOpt = franquiciaRepository.findById(franquiciaId);
        if (franquiciaOpt.isPresent()) {
            Franquicia franquicia = franquiciaOpt.get();
            franquicia.getSucursales().forEach(sucursal -> {
                if (sucursal.getId().equals(sucursalId)) {
                    sucursal.getProductos().forEach(producto -> {
                        if (producto.getId().equals(productoId)) {
                            producto.setStock(nuevoStock);
                        }
                    });
                }
            });
            return franquiciaRepository.save(franquicia);
        } else {
            throw new RuntimeException("Franquicia o sucursal no encontrada");
        }
    }

    @Override
    public List<Producto> obtenerProductosMayorStock(String franquiciaId) {
        Optional<Franquicia> franquiciaOpt = franquiciaRepository.findById(franquiciaId);
        if (franquiciaOpt.isPresent()) {
            Franquicia franquicia = franquiciaOpt.get();
            // Aquí puedes implementar la lógica para obtener los productos con mayor stock
            return franquicia.getSucursales().stream()
                    .flatMap(sucursal -> sucursal.getProductos().stream())
                    .filter(producto -> producto.getStock() > 0)
                    .toList(); // Devuelve la lista de productos en stock
        } else {
            throw new RuntimeException("Franquicia no encontrada");
        }
    }
}
