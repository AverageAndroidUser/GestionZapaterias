package com.example.Gestion.Entidades.Departamentos;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Departamentos {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID_Departamento;
    private String Nombre;

    public Departamentos(int iD_Departamento, String nombre) {
        ID_Departamento = iD_Departamento;
        Nombre = nombre;
    }

    public Departamentos() {
    }
    
    public int getID_Departamento() {
        return ID_Departamento;
    }
    public void setID_Departamento(int iD_Departamento) {
        ID_Departamento = iD_Departamento;
    }
    public String getNombre() {
        return Nombre;
    }
    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    
}
