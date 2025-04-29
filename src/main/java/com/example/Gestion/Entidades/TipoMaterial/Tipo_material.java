package com.example.Gestion.Entidades.TipoMaterial;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Tipo_material {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID_Tipo_material;
    private String Descripcion;
    private String Unidad_medida;

    public Tipo_material(int iD_Tipo_material, String Descripcion, String Unidad_medida) {
        ID_Tipo_material = iD_Tipo_material;
        this.Descripcion = Descripcion;
        this.Unidad_medida = Unidad_medida;
    }

    public Tipo_material() {
    }

    public int getID_Tipo_material() {
        return ID_Tipo_material;
    }

    public void setID_Tipo_material(int iD_Tipo_material) {
        ID_Tipo_material = iD_Tipo_material;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }
    public String getUnidad_medida() {
        return Unidad_medida;
    }
    public void setUnidad_medida(String Unidad_medida) {
        this.Unidad_medida = Unidad_medida;
    }
}
