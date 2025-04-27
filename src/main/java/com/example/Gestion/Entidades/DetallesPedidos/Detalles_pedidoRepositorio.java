package com.example.Gestion.Entidades.DetallesPedidos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Detalles_pedidoRepositorio extends JpaRepository<Detalles_pedidos, Integer> {
    
}
