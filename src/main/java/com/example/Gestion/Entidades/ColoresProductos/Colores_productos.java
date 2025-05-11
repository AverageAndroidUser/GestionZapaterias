package com.example.Gestion.Entidades.ColoresProductos;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Colores_productos {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID_Colores_Productos;
    private String Descripcion;
    
    public Colores_productos(int iD_Color_Producto, String descripcion) {
        ID_Colores_Productos = iD_Color_Producto;
        Descripcion = descripcion;
    }

    public Colores_productos() {
    }

    public int getID_Colores_Productos() {
        return ID_Colores_Productos;
    }

    public void setID_Colores_Productos(int iD_Color_Producto) {
        ID_Colores_Productos = iD_Color_Producto;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }
}
