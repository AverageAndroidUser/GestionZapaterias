package com.example.Gestion.Entidades.EstadoPedido;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Estado_pedidoRepositorio extends JpaRepository<Estado_pedido, Integer> {
    
}
