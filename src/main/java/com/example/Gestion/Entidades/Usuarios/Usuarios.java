package com.example.Gestion.Entidades.Usuarios;

import com.example.Gestion.Entidades.Municipios.Municipios;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Usuarios {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID_Usuarios;
    private String Zapateria;
    private String Encargado;
    private int Telefono;
    private String Celular;
    private String Correo;
    private String Direccion;
    private String Contraseña;

    @ManyToOne
    @JoinColumn(name = "ID_Municipio")
    private Municipios municipio;

    public Usuarios() {
        super();
    }

    public Usuarios(int ID_Usuarios, String Zapateria, String Encargado, int Telefono, String Celular, 
        String Correo, String Direccion, String Contraseña, Municipios Municipios){
            this.ID_Usuarios = ID_Usuarios;
            this.Zapateria = Zapateria;
            this.Encargado = Encargado;
            this.Telefono = Telefono;
            this.Celular = Celular;
            this.Correo = Correo;
            this.Direccion = Direccion;
            this.Contraseña = Contraseña;
            this.municipio = Municipios;
    }

    public int getID_Usuarios(){
        return ID_Usuarios;
    }
    public void setID_Usuarios(int ID_Usuarios){
        this.ID_Usuarios = ID_Usuarios;
    }
    
    public String getZapateria(){
        return Zapateria;
    }
    public void setZapateria(String Zapateria){
        this.Zapateria = Zapateria;
    }

    public String getEncargado(){
        return Encargado;
    }
    public void setEncargado(String Encargado){
        this.Encargado = Encargado;
    }

    public int getTelefono(){
        return Telefono;
    }
    public void setTelefono(int Telefono){
        this.Telefono = Telefono;
    }

    public String getCelular(){
        return Celular;
    }
    public void setCelular(String Celular){
        this.Celular = Celular;
    }

    public String getCorreo(){
        return Correo;
    }
    public void setCorreo(String Correo){
        this.Correo = Correo;
    }

    public String getDireccion(){
        return Direccion;
    }
    public void setDireccion(String Direccion){
        this.Direccion = Direccion;
    }

    public String getContraseña(){
        return Contraseña;
    }
    public void setContraseña(String Contraseña){
        this.Contraseña = Contraseña;
    }

    public Municipios getMunicipio() {
        return municipio;
    }

    public void setMunicipio(Municipios municipios) {
        this.municipio = municipios;
    }
}
