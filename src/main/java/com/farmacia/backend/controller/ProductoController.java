package com.farmacia.backend.controller;

import com.farmacia.backend.entity.ProductoEntity;
import com.farmacia.backend.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/productos")
@CrossOrigin(origins = "*") // Permitir peticiones desde cualquier origen
public class ProductoController {

    @Autowired
    private ProductoRepository productoRepository;

    // Obtener todos los productos
    @GetMapping
    public List<ProductoEntity> getAllProductos() {
        return productoRepository.findAll();
    }

    // Obtener un producto por su ID
    @GetMapping("/{id}")
    public ResponseEntity<ProductoEntity> getProductoById(@PathVariable String id) {
        Optional<ProductoEntity> producto = productoRepository.findById(id);
        return producto.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Crear un nuevo producto
    @PostMapping
    public ResponseEntity<ProductoEntity> createProducto(@RequestBody ProductoEntity producto) {
        try {
            ProductoEntity newProducto = productoRepository.save(producto);
            return ResponseEntity.status(HttpStatus.CREATED).body(newProducto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Actualizar un producto existente
    @PutMapping("/{id}")
    public ResponseEntity<ProductoEntity> updateProducto(@PathVariable String id, @RequestBody ProductoEntity productoDetails) {
        Optional<ProductoEntity> productoData = productoRepository.findById(id);

        if (productoData.isPresent()) {
            ProductoEntity producto = productoData.get();

            // Verificar y actualizar solo si los campos no son nulos o vacíos
            if (productoDetails.getNombre() != null && !productoDetails.getNombre().isEmpty()) {
                producto.setNombre(productoDetails.getNombre());
            }
            if (productoDetails.getcantidad() != null) {
                producto.setcantidad(productoDetails.getcantidad());
            }
            if (productoDetails.getFechaEntrada() != null) {
                producto.setFechaEntrada(productoDetails.getFechaEntrada());
            }
            if (productoDetails.getFechaCaducidad() != null) {
                producto.setFechaCaducidad(productoDetails.getFechaCaducidad());
            }
            if (productoDetails.getPrecio() != null) {
                producto.setPrecio(productoDetails.getPrecio());
            }
            if (productoDetails.getClasficiacion() != null && !productoDetails.getClasficiacion().isEmpty()) {
                producto.setClasficiacion(productoDetails.getClasficiacion());
            }
            if (productoDetails.getProveedor() != null && !productoDetails.getProveedor().isEmpty()) {
                producto.setProveedor(productoDetails.getProveedor());
            }

            ProductoEntity updatedProducto = productoRepository.save(producto);
            return ResponseEntity.ok(updatedProducto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


    // Eliminar un producto por su ID
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteProducto(@PathVariable String id) {
        try {
            productoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
