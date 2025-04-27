package com.example.Gestion.Controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.Gestion.Entidades.Departamentos.Departamentos;
import com.example.Gestion.Entidades.Municipios.Municipios;
import com.example.Gestion.Entidades.Municipios.MunicipiosRepositorio;

@Controller
@RequestMapping("/Municipios")
public class MunicipioDepControlador {
    
    @Autowired
    MunicipiosRepositorio repositorioMun;

    @GetMapping("/{id}")
    @ResponseBody
    public List<Municipios> municipioDep(@PathVariable int id){
        Departamentos departamento = new Departamentos();
        departamento.setID_Departamento(id);
        return repositorioMun.findByDepartamento(departamento);
    }
}
