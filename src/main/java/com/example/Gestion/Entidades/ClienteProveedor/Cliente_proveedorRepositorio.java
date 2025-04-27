package com.example.Gestion.Entidades.ClienteProveedor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Cliente_proveedorRepositorio extends JpaRepository<Cliente_proveedor, Integer> {
    
}
