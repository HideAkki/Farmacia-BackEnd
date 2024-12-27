package com.farmacia.backend.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.List;

@Document(collection = "facturas")
public class FacturaEntity {

    @Id
    private String id;
    private String fechaVenta;
    private Integer cantidad;
    private Double total;

    @DocumentReference
    private List<ProductoEntity> productos;

    public FacturaEntity() {
    }

    public FacturaEntity(String id, String fechaVenta, Integer cantidad, Double total, List<ProductoEntity> productos) {
        this.id = id;
        this.fechaVenta = fechaVenta;
        this.cantidad = cantidad;
        this.total = total;
        this.productos = productos;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(String fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public List<ProductoEntity> getProductos() {
        return productos;
    }

    public void setProductos(List<ProductoEntity> productos) {
        this.productos = productos;
    }
}
