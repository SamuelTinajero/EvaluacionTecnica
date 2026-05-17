package com.castores.inventario.service;

import com.castores.inventario.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UsuarioService usuarioService;

    public Usuario obtenerUsuarioAutenticado() {
        return usuarioService.obtenerUsuarioAutenticado();
    }
}
