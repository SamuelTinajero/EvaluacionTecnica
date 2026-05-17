package com.castores.inventario.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.Collections;

public class CustomUserDetails implements UserDetails {

    private Integer idUsuario;
    private String correo;
    private String contrasena;
    private String nombreRol;
    private Integer estatus;

    public CustomUserDetails(Integer idUsuario, String correo, String contrasena, 
                           String nombreRol, Integer estatus) {
        this.idUsuario = idUsuario;
        this.correo = correo;
        this.contrasena = contrasena;
        this.nombreRol = nombreRol;
        this.estatus = estatus;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + nombreRol));
    }

    @Override
    public String getPassword() {
        return contrasena;
    }

    @Override
    public String getUsername() {
        return correo;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return estatus == 1;
    }

    // Getters
    public Integer getIdUsuario() {
        return idUsuario;
    }

    public String getCorreo() {
        return correo;
    }

    public String getNombreRol() {
        return nombreRol;
    }

    public Integer getEstatus() {
        return estatus;
    }
}
