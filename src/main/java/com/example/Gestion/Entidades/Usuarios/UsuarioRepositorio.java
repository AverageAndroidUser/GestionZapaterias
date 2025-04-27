package com.example.Gestion.Entidades.Usuarios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UsuarioRepositorio extends JpaRepository<Usuarios, Integer>{
    
    @Query("SELECT u FROM Usuarios u WHERE u.Correo = ?1")
    public Usuarios findByCorreo(String Correo);
}
