package com.example.Gestion.Entidades.Municipios;

import com.example.Gestion.Entidades.Departamentos.Departamentos;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Municipios {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID_Municipios;
    private String Nombre;

    @ManyToOne
    @JoinColumn(name = "ID_Departamento")
    private Departamentos departamento;


    public Municipios(int iD_Municipios, String nombre, Departamentos departamento) {
        ID_Municipios = iD_Municipios;
        Nombre = nombre;
        this.departamento = departamento;
    }

    public Municipios() {
    }

    public int getID_Municipios() {
        return ID_Municipios;
    }

    public void setID_Municipios(int iD_Municipios) {
        ID_Municipios = iD_Municipios;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public Departamentos getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamentos departamento) {
        this.departamento = departamento;
    }

    
}
