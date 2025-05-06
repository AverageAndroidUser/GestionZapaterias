package com.example.Gestion.Entidades.Productos;

import com.example.Gestion.Entidades.Materiales.Materiales;
import com.example.Gestion.Entidades.TipoProducto.Tipo_producto;
import com.example.Gestion.Entidades.Usuarios.Usuarios;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Productos {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idProductos;
    private String Nombre;
    private String Descripcion;
    private int precioUnitario;
    private String Referencia;

    @ManyToOne
    @JoinColumn(name = "ID_Usuarios")
    private Usuarios usuario;

    @ManyToOne
    @JoinColumn(name = "ID_Tipo_Producto")
    private Tipo_producto tipo_producto;

    @ManyToOne
    @JoinColumn(name = "ID_Material")
    private Materiales materiales;

    public Productos() {
    }

    public Productos(int ID_Productos, String Nombre, String Descripcion, int Precio_unitario, String Referencia,
            Usuarios usuario, Tipo_producto tipo_producto, Materiales materiales) {
        this.idProductos = ID_Productos;
        this.Nombre = Nombre;
        this.Descripcion = Descripcion;
        this.precioUnitario = Precio_unitario;
        this.Referencia = Referencia;
        this.usuario = usuario;
        this.tipo_producto = tipo_producto;
        this.materiales = materiales;
    }

    public int getIdProductos() {
        return idProductos;
    }

    public void setIdProductos(int iD_Productos) {
        idProductos = iD_Productos;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public int getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(int precio_unitario) {
        precioUnitario = precio_unitario;
    }

    public String getReferencia() {
        return Referencia;
    }

    public void setReferencia(String referencia) {
        Referencia = referencia;
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    public Tipo_producto getTipo_producto() {
        return tipo_producto;
    }

    public void setTipo_producto(Tipo_producto tipo_producto) {
        this.tipo_producto = tipo_producto;
    }

    public Materiales getMateriales() {
        return materiales;
    }

    public void setMateriales(Materiales materiales) {
        this.materiales = materiales;
    }

    
}
