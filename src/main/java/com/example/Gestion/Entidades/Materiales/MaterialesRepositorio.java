package com.example.Gestion.Entidades.Materiales;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.Gestion.Entidades.Usuarios.Usuarios;

@Repository
public interface MaterialesRepositorio extends JpaRepository<Materiales, Integer> {

    Page<Materiales> findByUsuarios(Usuarios usaurio, Pageable page);
    
    @Query("SELECT m FROM Materiales m WHERE m.Nombre LIKE %?1% AND m.usuarios = ?2")
    List<Materiales> findByNombreAndUsuarios(String nombre, Usuarios usuario);

}
