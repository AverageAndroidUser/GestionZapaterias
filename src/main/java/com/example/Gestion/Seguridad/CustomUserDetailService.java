package com.example.Gestion.Seguridad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.Gestion.Entidades.Usuarios.Usuarios;
import com.example.Gestion.Entidades.Usuarios.UsuarioRepositorio;

public class CustomUserDetailService implements UserDetailsService{

    @Autowired
    UsuarioRepositorio repositorio;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuarios usuario = repositorio.findByCorreo(username);
        if(usuario == null){
            throw new UsernameNotFoundException("Correo no registrado...");
        }
        return new CustomUserDetails(usuario);
    }
    
    
}
