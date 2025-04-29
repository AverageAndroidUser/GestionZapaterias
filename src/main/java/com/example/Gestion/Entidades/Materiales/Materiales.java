package com.example.Gestion.Entidades.Materiales;

import com.example.Gestion.Entidades.ClienteProveedor.Cliente_proveedor;
import com.example.Gestion.Entidades.TipoMaterial.Tipo_material;
import com.example.Gestion.Entidades.Usuarios.Usuarios;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Materiales {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID_Materiales;
    private String Nombre;
    private String Descripcion;
    private int cantidad;
    private int costoUnidad;
    private int stockMinimo;
    private String fechaActualizacion;

    @ManyToOne
    @JoinColumn(name = "ID_Usuarios") //Nombre en la Base de datos
    private Usuarios usuarios;

    @ManyToOne
    @JoinColumn(name = "ID_Cliente_proveedor")
    private Cliente_proveedor clienteProveedores;
    
    @ManyToOne
    @JoinColumn(name = "ID_Tipo_material")
    private Tipo_material tipoMaterial;

    public Materiales(int iD_Materiales, String nombre, String descripcion, int cantidad, int costo_unidad,
            int stock_minimo, Usuarios usuarios, Cliente_proveedor cliente_proveedor, Tipo_material tipo_material, String fecha_actualizacion) {
        ID_Materiales = iD_Materiales;
        Nombre = nombre;
        Descripcion = descripcion;
        this.cantidad = cantidad;
        costoUnidad = costo_unidad;
        stockMinimo = stock_minimo;
        this.usuarios = usuarios;
        this.clienteProveedores = cliente_proveedor;
        this.tipoMaterial = tipo_material;
        this.fechaActualizacion = fecha_actualizacion;
    }

    public Materiales() {
    }

    public int getID_Materiales() {
        return ID_Materiales;
    }

    public void setID_Materiales(int iD_Materiales) {
        ID_Materiales = iD_Materiales;
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

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getCostoUnidad() {
        return costoUnidad;
    }

    public void setCostoUnidad(int costo_unidad) {
        costoUnidad = costo_unidad;
    }

    public int getStockMinimo() {
        return stockMinimo;
    }

    public void setStockMinimo(int stock_minimo) {
        stockMinimo = stock_minimo;
    }

    public Usuarios getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Usuarios usuarios) {
        this.usuarios = usuarios;
    }

    public Cliente_proveedor getClienteProveedores() {
        return clienteProveedores;
    }

    public void setClienteProveedores(Cliente_proveedor cliente_proveedor) {
        this.clienteProveedores = cliente_proveedor;
    }

    public Tipo_material getTipoMaterial() {
        return tipoMaterial;
    }

    public void setTipoMaterial(Tipo_material tipo_material) {
        this.tipoMaterial = tipo_material;
    }

    public String getFechaActualizacion() {
        return fechaActualizacion;
    }
    public void setFechaActualizacion(String fecha_actualizacion) {
        fechaActualizacion = fecha_actualizacion;
    }
    
}
