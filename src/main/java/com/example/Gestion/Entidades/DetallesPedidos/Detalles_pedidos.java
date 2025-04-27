package com.example.Gestion.Entidades.DetallesPedidos;

import com.example.Gestion.Entidades.ColoresProductos.Colores_productos;
import com.example.Gestion.Entidades.Productos.Productos;
import com.example.Gestion.Entidades.TallasProductos.Tallas_productos;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Detalles_pedidos {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID_Detalles_pedidos;
    private int Subtotal;
    private int Cantidad;

    @ManyToOne
    @JoinColumn(name = "ID_Tallas_Producto")
    private Tallas_productos tallas_producto;

    @ManyToOne
    @JoinColumn(name = "ID_Colores_Producto")
    private Colores_productos colores_productos;

    @ManyToOne
    @JoinColumn(name = "ID_Productos")
    private Productos productos;

    public Detalles_pedidos() {
    }

    public Detalles_pedidos(int ID_Detalles_pedidos, int Subtotal, int Cantidad, Tallas_productos tallas_producto,
            Colores_productos colores_productos, Productos productos) {
        this.ID_Detalles_pedidos = ID_Detalles_pedidos;
        this.Subtotal = Subtotal;
        this.Cantidad = Cantidad;
        this.tallas_producto = tallas_producto;
        this.colores_productos = colores_productos;
        this.productos = productos;
    }

    public int getID_Detalles_pedidos() {
        return ID_Detalles_pedidos;
    }

    public void setID_Detalles_pedidos(int iD_Detalles_pedidos) {
        ID_Detalles_pedidos = iD_Detalles_pedidos;
    }

    public int getSubtotal() {
        return Subtotal;
    }

    public void setSubtotal(int subtotal) {
        Subtotal = subtotal;
    }

    public int getCantidad() {
        return Cantidad;
    }

    public void setCantidad(int cantidad) {
        Cantidad = cantidad;
    }

    public Tallas_productos getTallas_producto() {
        return tallas_producto;
    }

    public void setTallas_producto(Tallas_productos tallas_producto) {
        this.tallas_producto = tallas_producto;
    }

    public Colores_productos getColores_productos() {
        return colores_productos;
    }

    public void setColores_productos(Colores_productos colores_productos) {
        this.colores_productos = colores_productos;
    }

    public Productos getProductos() {
        return productos;
    }

    public void setProductos(Productos productos) {
        this.productos = productos;
    }

    
}
