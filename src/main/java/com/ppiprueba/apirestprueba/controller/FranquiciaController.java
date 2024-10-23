package com.ppiprueba.apirestprueba.controller;


import com.ppiprueba.apirestprueba.model.Franquicia;
import com.ppiprueba.apirestprueba.model.Sucursal;
import com.ppiprueba.apirestprueba.model.Producto;
import com.ppiprueba.apirestprueba.service.FranquiciaService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/franquicias")
public class FranquiciaController {

    private final FranquiciaService franquiciaService;

    public FranquiciaController(FranquiciaService franquiciaService) {
        this.franquiciaService = franquiciaService;
    }


    //  Endpoints de las franquicias 
    // 1. Endpoint para agregar una nueva franquicia
    @PostMapping
    public Franquicia agregarFranquicia(@RequestBody Franquicia franquicia) {
        return franquiciaService.crearFranquicia(franquicia);
    }

    // 2. Endpoint para obtener una franquicia por ID
    @GetMapping("/{id}")
    public Optional<Franquicia> obtenerFranquiciaPorId(@PathVariable String id) {
        return franquiciaService.obtenerFranquiciaPorId(id);
    }

    // 3. Endpoint para obtener todas las franquicias 
    @GetMapping
    public List<Franquicia> obtenerFranquicias() {
        return franquiciaService.obtenerTodasFranquicias();
    }

    // 4. Endpoint para modificar nombre de la franquicia
    @PatchMapping("/{franquiciaId}/nombre")
    public Franquicia modificarNombreFranquicia(@PathVariable String franquiciaId, @RequestParam String nuevoNombre) {
    return franquiciaService.modificarNombreFranquicia(franquiciaId, nuevoNombre);
    }


    //Endpoints sucursales y productos
    // 1. Endpoint para agregar una nueva sucursal a una franquicia
    @PostMapping("/{franquiciaId}/sucursales")
    public Franquicia agregarSucursal(@PathVariable String franquiciaId, @RequestBody Sucursal sucursal) {
        return franquiciaService.agregarSucursal(franquiciaId, sucursal);
    }

    //2.Endpoint para agregar un nuevo producto a una sucursal 
    @PostMapping("/{franquiciaId}/sucursales/{sucursalId}/productos")
    public Franquicia agregarProducto(@PathVariable String franquiciaId, @PathVariable String sucursalId,@RequestBody Producto producto) {
        return franquiciaService.agregarProducto(franquiciaId, sucursalId, producto);
    }

    // 3. Endpoint para eliminar un producto en una sucursal
    @DeleteMapping("/{franquiciaId}/sucursales/{sucursalId}/productos/{productoId}")
    public Franquicia eliminarProducto(@PathVariable String franquiciaId, @PathVariable String sucursalId, @PathVariable String productoId) {
        return franquiciaService.eliminarProducto(franquiciaId, sucursalId, productoId);
    }

    // 4. Endpoint para modificar el stock de un producto
    @PatchMapping("/{franquiciaId}/sucursales/{sucursalId}/productos/{productoId}/stock")
    public ResponseEntity<Franquicia> modificarStockProducto(@PathVariable String franquiciaId,@PathVariable String sucursalId,@PathVariable String productoId,@RequestParam int nuevoStock) {
        Franquicia franquiciaActualizada = franquiciaService.modificarStockProducto(franquiciaId, sucursalId, productoId, nuevoStock);
        
        if (franquiciaActualizada != null) {
            return ResponseEntity.ok(franquiciaActualizada); 
        } else {
            return ResponseEntity.notFound().build(); 
        }
    }

    // 5. Endpoint para modificar un producto en una sucursal (nombre, stock)
    @PutMapping("/{franquiciaId}/sucursales/{sucursalId}/productos/{productoId}")
    public Franquicia modificarProducto(@PathVariable String franquiciaId, @PathVariable String sucursalId, @PathVariable String productoId, @RequestBody Producto productoActualizado) {
        return franquiciaService.modificarProducto(franquiciaId, sucursalId, productoId, productoActualizado);
    }

    // 6. Endpoint para obtener el producto con más stock por sucursal de una franquicia
    @GetMapping("/{franquiciaId}/productos-max-stock")
    public List<Producto> obtenerProductoMayorStock(@PathVariable String franquiciaId) {
        return franquiciaService.obtenerProductosMayorStock(franquiciaId);
    }

    

    
}
