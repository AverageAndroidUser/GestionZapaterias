package com.example.Gestion.Entidades.ClienteProveedor;

import com.example.Gestion.Entidades.Municipios.Municipios;
import com.example.Gestion.Entidades.Usuarios.Usuarios;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Cliente_proveedor {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID_Cliente_proveedor;
    private String Nombre;
    private int Telefono;
    private String Celular;
    private String Direccion;
    private String Correo;
    private Boolean Tipo_cliente_proveedor; // true = cliente, false = proveedor

    @ManyToOne
    @JoinColumn(name = "ID_Usuarios")
    private Usuarios usuarios;

    @ManyToOne
    @JoinColumn(name = "ID_Municipios")
    private Municipios municipio;

    public Cliente_proveedor(int iD_Cliente_proveedor, String nombre, int telefono, String celular, String direccion,
            String correo, Boolean tipo_cliente_proveedor, Usuarios usuarios, Municipios municipios) {
        ID_Cliente_proveedor = iD_Cliente_proveedor;
        Nombre = nombre;
        Telefono = telefono;
        Celular = celular;
        Direccion = direccion;
        Correo = correo;
        Tipo_cliente_proveedor = tipo_cliente_proveedor;
        this.usuarios = usuarios;
        this.municipio = municipios;
    }
    public Cliente_proveedor() {
    }
    
    public int getID_Cliente_proveedor() {
        return ID_Cliente_proveedor;
    }
    public void setID_Cliente_proveedor(int iD_Cliente_proveedor) {
        ID_Cliente_proveedor = iD_Cliente_proveedor;
    }
    public String getNombre() {
        return Nombre;
    }
    public void setNombre(String nombre) {
        Nombre = nombre;
    }
    public int getTelefono() {
        return Telefono;
    }
    public void setTelefono(int telefono) {
        Telefono = telefono;
    }
    public String getCelular() {
        return Celular;
    }
    public void setCelular(String celular) {
        Celular = celular;
    }
    public String getDireccion() {
        return Direccion;
    }
    public void setDireccion(String direccion) {
        Direccion = direccion;
    }
    public String getCorreo() {
        return Correo;
    }
    public void setCorreo(String correo) {
        Correo = correo;
    }
    public Boolean getTipo_cliente_proveedor() {
        return Tipo_cliente_proveedor;
    }
    public void setTipo_cliente_proveedor(Boolean tipo_cliente_proveedor) {
        Tipo_cliente_proveedor = tipo_cliente_proveedor;
    }
    public Usuarios getUsuarios() {
        return usuarios;
    }
    public void setUsuarios(Usuarios usuarios) {
        this.usuarios = usuarios;
    }
    public Municipios getMunicipio() {
        return municipio;
    }
    public void setMunicipio(Municipios municipios) {
        this.municipio = municipios;
    }

    
}
