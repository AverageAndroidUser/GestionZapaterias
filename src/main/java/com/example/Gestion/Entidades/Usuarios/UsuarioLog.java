package com.example.Gestion.Entidades.Usuarios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class UsuarioLog {
    
    @Autowired UsuarioRepositorio repositorio;

    public Usuarios correoUsuario(){
        Object usuario = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String CorreoUsuario;
        if(usuario instanceof UserDetails){
            CorreoUsuario = (((UserDetails) usuario).getUsername());
        }else{
            CorreoUsuario = usuario.toString();
        }
        Usuarios correo2 = repositorio.findByCorreo(CorreoUsuario);
        return correo2;
    }
}
