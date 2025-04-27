package com.example.Gestion.Entidades.TipoProducto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Tipo_producto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID_Tipo_Producto;
    private String Descripcion;

    public Tipo_producto(int iD_Tipo_Producto, String descripcion) {
        ID_Tipo_Producto = iD_Tipo_Producto;
        Descripcion = descripcion;
    }

    public Tipo_producto() {
    }

    public int getID_Tipo_Producto() {
        return ID_Tipo_Producto;
    }

    public void setID_Tipo_Producto(int iD_Tipo_Producto) {
        ID_Tipo_Producto = iD_Tipo_Producto;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    
    
}
