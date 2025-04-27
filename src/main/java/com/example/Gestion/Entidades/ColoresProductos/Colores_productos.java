package com.example.Gestion.Entidades.ColoresProductos;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Colores_productos {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID_Color_Producto;
    private String Descripcion;
    
    public Colores_productos(int iD_Color_Producto, String descripcion) {
        ID_Color_Producto = iD_Color_Producto;
        Descripcion = descripcion;
    }

    public Colores_productos() {
    }

    public int getID_Color_Producto() {
        return ID_Color_Producto;
    }

    public void setID_Color_Producto(int iD_Color_Producto) {
        ID_Color_Producto = iD_Color_Producto;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }
}
