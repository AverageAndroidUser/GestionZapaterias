package com.example.Gestion.Entidades.TipoMaterial;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Tipo_materialRepositorio extends JpaRepository<Tipo_material, Integer> {
    
}
