package com.example.Gestion.Entidades.EstadoPedido;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Estado_pedido {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID_Estado_Pedido;
    private String Descripcion;
    
    public Estado_pedido(int iD_Estado_pedido, String nombre) {
        ID_Estado_Pedido = iD_Estado_pedido;
        Descripcion = nombre;
    }
    public Estado_pedido() {
    }
    public int getID_Estado_Pedido() {
        return ID_Estado_Pedido;
    }
    public void setID_Estado_Pedido(int iD_Estado_pedido) {
        ID_Estado_Pedido = iD_Estado_pedido;
    }
    public String geDescripcion() {
        return Descripcion;
    }
    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    
    
}
