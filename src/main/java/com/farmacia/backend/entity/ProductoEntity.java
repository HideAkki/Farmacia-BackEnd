package com.farmacia.backend.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "productos")
public class ProductoEntity {

    @Id
    private String id;
    private String nombre;
    private Integer cantidad;
    private String fechaEntrada;
    private String fechaCaducidad;
    private Double precio;
    private String clasficiacion;
    private String proveedor;

    public ProductoEntity() {
    }

    public ProductoEntity(String id, String nombre, Integer cantidad, String fechaEntrada, String fechaCaducidad, Double precio, String clasficiacion, String proveedor) {
        this.id = id;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.fechaEntrada = fechaEntrada;
        this.fechaCaducidad = fechaCaducidad;
        this.precio = precio;
        this.clasficiacion = clasficiacion;
        this.proveedor = proveedor;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getcantidad() {
        return cantidad;
    }

    public void setcantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public String getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(String fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public String getFechaCaducidad() {
        return fechaCaducidad;
    }

    public void setFechaCaducidad(String fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public String getClasficiacion() {
        return clasficiacion;
    }

    public void setClasficiacion(String clasficiacion) {
        this.clasficiacion = clasficiacion;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }
}
