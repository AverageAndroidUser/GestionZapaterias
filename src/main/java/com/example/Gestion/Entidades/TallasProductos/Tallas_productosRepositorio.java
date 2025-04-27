package com.example.Gestion.Entidades.TallasProductos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Tallas_productosRepositorio extends JpaRepository<Tallas_productos, Integer> {
    
}
