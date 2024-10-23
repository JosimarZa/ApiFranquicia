package com.ppiprueba.apirestprueba.service;

import com.ppiprueba.apirestprueba.model.Franquicia;
import com.ppiprueba.apirestprueba.model.Producto;
import com.ppiprueba.apirestprueba.model.Sucursal;
import java.util.List;
import java.util.Optional;

public interface FranquiciaService {
    List<Franquicia> obtenerTodasFranquicias();
    Optional<Franquicia> obtenerFranquiciaPorId(String id);
    Franquicia crearFranquicia(Franquicia franquicia);
    Franquicia agregarSucursal(String franquiciaId, Sucursal sucursal);
    Franquicia eliminarProducto(String franquiciaId, String sucursalId, String productoId);
    Franquicia modificarStockProducto(String franquiciaId, String sucursalId, String productoId, int nuevoStock);
    Franquicia modificarProducto(String franquiciaId, String sucursalId, String productoId, Producto productoActualizado); // Nuevo método
    List<Producto> obtenerProductosMayorStock(String franquiciaId);
    Franquicia agregarProducto(String franquiciaId, String sucursalId, Producto producto);
    Franquicia modificarNombreFranquicia(String franquiciaId, String nuevoNombre);
}

