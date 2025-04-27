package com.example.Gestion.Controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.Gestion.Entidades.Usuarios.UsuarioLog;
import com.example.Gestion.Entidades.Usuarios.UsuarioRepositorio;

@Controller
@RequestMapping("/GestionZapaterias/Usuarios")
public class UsuarioControlador {
    
    @Autowired UsuarioLog loggin;
    @Autowired UsuarioRepositorio repositorio;



}
