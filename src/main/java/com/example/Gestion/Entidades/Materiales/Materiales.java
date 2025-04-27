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
    private int Cantidad;
    private int Costo_unidad;
    private int Stock_minimo;
    private String Fecha_actualizacion;

    @ManyToOne
    @JoinColumn(name = "ID_Usuario")
    private Usuarios Usuarios;

    @ManyToOne
    @JoinColumn(name = "ID_Cliente_proveedore")
    private Cliente_proveedor Cliente_proveedor;
    
    @ManyToOne
    @JoinColumn(name = "ID_Tipo_material")
    private Tipo_material Tipo_material;

    public Materiales(int iD_Materiales, String nombre, String descripcion, int cantidad, int costo_unidad,
            int stock_minimo, Usuarios usuarios, Cliente_proveedor cliente_proveedor, Tipo_material tipo_material, String fecha_actualizacion) {
        ID_Materiales = iD_Materiales;
        Nombre = nombre;
        Descripcion = descripcion;
        Cantidad = cantidad;
        Costo_unidad = costo_unidad;
        Stock_minimo = stock_minimo;
        this.Usuarios = usuarios;
        this.Cliente_proveedor = cliente_proveedor;
        this.Tipo_material = tipo_material;
        this.Fecha_actualizacion = fecha_actualizacion;
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
        return Cantidad;
    }

    public void setCantidad(int cantidad) {
        Cantidad = cantidad;
    }

    public int getCosto_unidad() {
        return Costo_unidad;
    }

    public void setCosto_unidad(int costo_unidad) {
        Costo_unidad = costo_unidad;
    }

    public int getStock_minimo() {
        return Stock_minimo;
    }

    public void setStock_minimo(int stock_minimo) {
        Stock_minimo = stock_minimo;
    }

    public Usuarios getUsuarios() {
        return Usuarios;
    }

    public void setUsuarios(Usuarios usuarios) {
        this.Usuarios = usuarios;
    }

    public Cliente_proveedor getCliente_proveedor() {
        return Cliente_proveedor;
    }

    public void setCliente_proveedor(Cliente_proveedor cliente_proveedor) {
        this.Cliente_proveedor = cliente_proveedor;
    }

    public Tipo_material getTipo_material() {
        return Tipo_material;
    }

    public void setTipo_material(Tipo_material tipo_material) {
        this.Tipo_material = tipo_material;
    }

    public String getFecha_actualizacion() {
        return Fecha_actualizacion;
    }
    public void setFecha_actualizacion(String fecha_actualizacion) {
        Fecha_actualizacion = fecha_actualizacion;
    }
    
}
