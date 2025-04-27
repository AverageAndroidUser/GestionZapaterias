package com.example.Gestion.Entidades.Municipios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Gestion.Entidades.Departamentos.Departamentos;

@Repository
public interface MunicipiosRepositorio extends JpaRepository<Municipios, Integer>{
    
    List<Municipios> findByDepartamento(Departamentos departamentos);
}
