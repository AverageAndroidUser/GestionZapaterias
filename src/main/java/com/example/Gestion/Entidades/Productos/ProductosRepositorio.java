package com.example.Gestion.Entidades.Productos;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.Gestion.Entidades.Usuarios.Usuarios;

@Repository
public interface ProductosRepositorio extends JpaRepository<Productos, Integer> {

    Page<Productos> findByUsuario(Usuarios usuario, Pageable page);

    @Query("SELECT p FROM Productos p WHERE p.Nombre LIKE %?1% AND p.usuario = ?2")
    List<Productos> findByNombreAndUsuarios(String nombre, Usuarios usuario);
    
    
}
