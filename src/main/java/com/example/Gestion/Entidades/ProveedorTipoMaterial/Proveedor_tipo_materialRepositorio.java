package com.example.Gestion.Entidades.ProveedorTipoMaterial;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Proveedor_tipo_materialRepositorio extends JpaRepository<Proveedor_tipo_material, Integer> {
    
}
