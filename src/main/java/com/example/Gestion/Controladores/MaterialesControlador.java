package com.example.Gestion.Controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/GestionZapaterias/Materiales")
public class MaterialesControlador {
    
    @GetMapping("/0")
    public String listaMateriales(){
        return "Materiales/ListaMateriales";
    }
}
