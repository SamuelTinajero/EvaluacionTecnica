package com.castores.inventario.controller;

import com.castores.inventario.entity.Usuario;
import com.castores.inventario.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    private AuthService authService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/home")
    public String home(Model model) {
        Usuario usuario = authService.obtenerUsuarioAutenticado();
        model.addAttribute("usuario", usuario);
        model.addAttribute("rol", usuario.getRol().getNombre());
        return "home";
    }

    @GetMapping("/error/403")
    public String accesoDenegado() {
        return "error/403";
    }
}
