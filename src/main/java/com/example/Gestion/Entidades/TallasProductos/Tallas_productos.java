package com.example.Gestion.Entidades.TallasProductos;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Tallas_productos {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID_Tallas_productos;
    private int Numero;

    public Tallas_productos(int iD_Tallas_productos, int numero) {
        ID_Tallas_productos = iD_Tallas_productos;
        Numero = numero;
    }

    public Tallas_productos() {
    }

    public int getID_Tallas_productos() {
        return ID_Tallas_productos;
    }

    public void setID_Tallas_productos(int iD_Tallas_productos) {
        ID_Tallas_productos = iD_Tallas_productos;
    }

    public int getNumero() {
        return Numero;
    }

    public void setNumero(int numero) {
        Numero = numero;
    }
}
