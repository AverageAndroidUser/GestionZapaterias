package com.example.Gestion.Entidades.ProveedorTipoMaterial;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.Gestion.Entidades.ClienteProveedor.Cliente_proveedor;

@Repository
public interface Proveedor_tipo_materialRepositorio extends JpaRepository<Proveedor_tipo_material, Integer> {

    @Query("SELECT p FROM Proveedor_tipo_material p WHERE p.Cliente_proveedor = ?1")
    List<Proveedor_tipo_material> findByProveedor(Cliente_proveedor cliente_proveedor);
    
}
