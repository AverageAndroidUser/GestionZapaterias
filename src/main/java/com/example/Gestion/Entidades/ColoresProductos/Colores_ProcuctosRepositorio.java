package com.example.Gestion.Entidades.ColoresProductos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Colores_ProcuctosRepositorio extends JpaRepository<Colores_productos, Integer> {
    
}
