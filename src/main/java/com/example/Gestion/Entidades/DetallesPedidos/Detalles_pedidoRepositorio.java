package com.example.Gestion.Entidades.DetallesPedidos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Gestion.Entidades.Pedidos.Pedidos;

@Repository
public interface Detalles_pedidoRepositorio extends JpaRepository<Detalles_pedidos, Integer> {

    List<Detalles_pedidos> findByPedidos(Pedidos pedido);
    
}
