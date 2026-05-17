package com.castores.inventario.controller;

import com.castores.inventario.entity.Producto;
import com.castores.inventario.entity.Usuario;
import com.castores.inventario.service.ProductoService;
import com.castores.inventario.service.InventarioService;
import com.castores.inventario.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.List;

@Controller
@RequestMapping("/inventario")
public class MovimientoController {

    @Autowired
    private ProductoService productoService;

    @Autowired
    private InventarioService inventarioService;

    @Autowired
    private AuthService authService;

    @GetMapping
    public String listar(Model model) {
        List<Producto> productos = productoService.listarTodos();
        model.addAttribute("productos", productos);
        return "inventario/lista";
    }

    @GetMapping("/entrada")
    public String entrada(Model model) {
        List<Producto> productos = productoService.listarActivos();
        model.addAttribute("productos", productos);
        return "inventario/entrada";
    }

    @PostMapping("/entrada")
    public String procesarEntrada(@RequestParam Integer idProducto,
            @RequestParam Integer cantidad,
            RedirectAttributes redirectAttributes) {
        try {
            Usuario usuario = authService.obtenerUsuarioAutenticado();
            inventarioService.entrada(idProducto, cantidad, usuario);
            redirectAttributes.addFlashAttribute("exito", "Entrada registrada correctamente");
            return "redirect:/inventario";
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/inventario/entrada";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error",
                    "No pudimos procesar la entrada en este momento. Por favor, intenta más tarde o contacta a soporte.");
            return "redirect:/inventario/entrada";
        }
    }

    @GetMapping("/salida")
    public String salida(Model model) {
        List<Producto> productos = productoService.listarActivos();
        model.addAttribute("productos", productos);
        return "inventario/salida";
    }

    @PostMapping("/salida")
    public String procesarSalida(@RequestParam Integer idProducto,
            @RequestParam Integer cantidad,
            RedirectAttributes redirectAttributes) {
        try {
            Usuario usuario = authService.obtenerUsuarioAutenticado();
            inventarioService.salida(idProducto, cantidad, usuario);
            redirectAttributes.addFlashAttribute("exito", "Salida registrada correctamente");
            return "redirect:/inventario";
        } catch (IllegalArgumentException e) {
                        redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/inventario/salida";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error",
                    "No pudimos procesar la entrada en este momento. Por favor, intenta más tarde o contacta a soporte.");
            return "redirect:/inventario/salida";
        }
    }
}
