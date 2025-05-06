package com.example.Gestion.Controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/")
public class LandingControlador {
    @GetMapping("")
    public String home(){
        return "redirect:/GestionZapaterias/Home";
    }
}
