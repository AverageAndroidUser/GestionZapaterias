package com.example.Gestion.Entidades.TipoProducto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Tipo_productoRepositorio extends JpaRepository<Tipo_producto, Integer> {
    
}
