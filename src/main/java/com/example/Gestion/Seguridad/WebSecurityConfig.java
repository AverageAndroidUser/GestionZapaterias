package com.example.Gestion.Seguridad;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig {
    
    @Bean
    UserDetailsService userDetailsService(){
        return new CustomUserDetailService();
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    SecurityFilterChain configure(HttpSecurity http) throws Exception{
        http.authenticationProvider(authenticationProvider());

        http.authorizeHttpRequests(auth -> 
            auth.requestMatchers("/GestionZapaterias/guardarUsuario", "/Municipios/", "/GestionZapaterias/Home").permitAll()
            .requestMatchers("/GestionZapaterias/**").authenticated()
            .anyRequest().permitAll()
            )
            .formLogin(login -> 
                login.loginPage("/GestionZapaterias/register")
                .loginProcessingUrl("/login") // URL para el formulario de inicio de sesión, se encuentra en registrarForm <form method="post" th:action="@{/login}">
                .failureUrl("/GestionZapaterias/error")
                .usernameParameter("Correo")
                .passwordParameter("Contraseña")
                .defaultSuccessUrl("/GestionZapaterias/Materiales/0")
                .permitAll()
                )
                .logout(logout -> logout.logoutSuccessUrl("/GestionZapaterias/register").permitAll()
            ).exceptionHandling().accessDeniedPage("/error");
            return http.build();
    }
  
}
