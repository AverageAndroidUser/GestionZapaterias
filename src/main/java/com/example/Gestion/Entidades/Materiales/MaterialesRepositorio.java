package com.example.Gestion.Entidades.Materiales;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaterialesRepositorio extends JpaRepository<Materiales, Integer> {
    
}
