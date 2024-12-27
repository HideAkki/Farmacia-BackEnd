package com.farmacia.backend.controller;

import com.farmacia.backend.entity.FacturaEntity;
import com.farmacia.backend.entity.ProductoEntity;
import com.farmacia.backend.repository.FacturaRepository;
import com.farmacia.backend.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/facturas")
@CrossOrigin(origins = "*")
public class FacturaController {

    @Autowired
    private FacturaRepository facturaRepository;

    @Autowired
    private ProductoRepository productoRepository;

    // Obtener todas las facturas
    @GetMapping
    public List<FacturaEntity> getAllFacturas() {
        return facturaRepository.findAll();
    }

    // Obtener una factura por su ID
    @GetMapping("/{id}")
    public ResponseEntity<FacturaEntity> getFacturaById(@PathVariable String id) {
        Optional<FacturaEntity> factura = facturaRepository.findById(id);
        return factura.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Crear una nueva factura
    @PostMapping
    public ResponseEntity<FacturaEntity> createFactura(@RequestBody FacturaEntity factura) {
        try {
            // Validar si los productos existen
            if (factura.getProductos() != null && !factura.getProductos().isEmpty()) {
                for (ProductoEntity producto : factura.getProductos()) {
                    if (!productoRepository.existsById(producto.getId())) {
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
                    }
                }
            }

            FacturaEntity newFactura = facturaRepository.save(factura);
            return ResponseEntity.status(HttpStatus.CREATED).body(newFactura);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Actualizar una factura existente
    @PutMapping("/{id}")
    public ResponseEntity<FacturaEntity> updateFactura(@PathVariable String id, @RequestBody FacturaEntity facturaDetails) {
        Optional<FacturaEntity> facturaData = facturaRepository.findById(id);

        if (facturaData.isPresent()) {
            FacturaEntity factura = facturaData.get();
            factura.setFechaVenta(facturaDetails.getFechaVenta());
            factura.setCantidad(facturaDetails.getCantidad());
            factura.setTotal(facturaDetails.getTotal());

            // Validar si los productos existen
            if (facturaDetails.getProductos() != null && !facturaDetails.getProductos().isEmpty()) {
                for (ProductoEntity producto : facturaDetails.getProductos()) {
                    if (!productoRepository.existsById(producto.getId())) {
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
                    }
                }
            }

            factura.setProductos(facturaDetails.getProductos());
            FacturaEntity updatedFactura = facturaRepository.save(factura);
            return ResponseEntity.ok(updatedFactura);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Eliminar una factura por su ID
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteFactura(@PathVariable String id) {
        try {
            facturaRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
