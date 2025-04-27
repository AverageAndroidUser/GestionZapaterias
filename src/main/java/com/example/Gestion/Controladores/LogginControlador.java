package com.example.Gestion.Controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.Gestion.Entidades.Departamentos.DepartamentoRepositorio;
import com.example.Gestion.Entidades.Usuarios.Usuarios;
import com.example.Gestion.Entidades.Usuarios.UsuarioRepositorio;

@Controller
@RequestMapping("/GestionZapaterias")
public class LogginControlador {
    
    @Autowired UsuarioRepositorio repositorioUsu;
    @Autowired DepartamentoRepositorio repositorioDep;

    @GetMapping("/register")
    public String registrar(Model modelo){
        modelo.addAttribute("Departamentoss", repositorioDep.findAll());
        modelo.addAttribute("Usuarioss", new Usuarios());
        return "registrarForm";
    }

    @PostMapping("guardarUsuario")
    public String guardar(Usuarios usuario){
        BCryptPasswordEncoder password = new BCryptPasswordEncoder();
        String contraseña = password.encode(usuario.getContraseña());
        usuario.setContraseña(contraseña);
        repositorioUsu.save(usuario);
        return "correcto";
    }
    
    @GetMapping("Home")
    public String home(){
        return "Home/Home";
    }
}
