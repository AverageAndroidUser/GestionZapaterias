package com.example.Gestion.Entidades.ClienteProveedor;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.Gestion.Entidades.Usuarios.Usuarios;

@Repository
public interface Cliente_proveedorRepositorio extends JpaRepository<Cliente_proveedor, Integer> {

    @Query("SELECT c FROM Cliente_proveedor c WHERE c.usuarios = ?1 AND c.Tipo_cliente_proveedor = ?2")
    List<Cliente_proveedor> findByUsuarios(Usuarios usuario, Boolean tipo_cliente_proveedor);
    
}
