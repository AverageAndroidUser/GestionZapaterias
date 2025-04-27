package com.example.Gestion.Entidades.ProveedorTipoMaterial;

import com.example.Gestion.Entidades.ClienteProveedor.Cliente_proveedor;
import com.example.Gestion.Entidades.TipoMaterial.Tipo_material;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Proveedor_tipo_material {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID_Proveedor_tipo_material;

    @ManyToOne
    @JoinColumn(name = "ID_Cliente_Proveedor")
    private Cliente_proveedor Cliente_proveedor;

    @ManyToOne
    @JoinColumn(name = "ID_Tipo_Material")
    private Tipo_material Tipo_material;

    public Proveedor_tipo_material(int iD_Proveedor_tipo_material, Cliente_proveedor cliente_proveedor,
            Tipo_material tipo_material) {
        ID_Proveedor_tipo_material = iD_Proveedor_tipo_material;
        Cliente_proveedor = cliente_proveedor;
        Tipo_material = tipo_material;
    }
    
    public Proveedor_tipo_material() {
    }

    public int getID_Proveedor_tipo_material() {
        return ID_Proveedor_tipo_material;
    }

    public void setID_Proveedor_tipo_material(int iD_Proveedor_tipo_material) {
        ID_Proveedor_tipo_material = iD_Proveedor_tipo_material;
    }

    public Cliente_proveedor getCliente_proveedor() {
        return Cliente_proveedor;
    }

    public void setCliente_proveedor(Cliente_proveedor cliente_proveedor) {
        Cliente_proveedor = cliente_proveedor;
    }

    public Tipo_material getTipo_material() {
        return Tipo_material;
    }

    public void setTipo_material(Tipo_material tipo_material) {
        Tipo_material = tipo_material;
    }

    
}
