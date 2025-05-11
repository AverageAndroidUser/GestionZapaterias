package com.example.Gestion.Entidades.Pedidos;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Gestion.Entidades.Usuarios.Usuarios;

@Repository
public interface PedidosRepositorio extends JpaRepository<Pedidos, Integer> {

    Page<Pedidos> findByUsuario(Usuarios usuario, Pageable pageable);
    
}
