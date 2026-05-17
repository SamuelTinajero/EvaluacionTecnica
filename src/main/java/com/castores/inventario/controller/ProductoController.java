package com.castores.inventario.controller;

import com.castores.inventario.dto.ProductoDTO;
import com.castores.inventario.entity.Producto;
import com.castores.inventario.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("producto", new Producto());
        return "productos/nuevo";
    }

    @PostMapping("/guardar")
    public String guardar(ProductoDTO dto,
            @RequestParam(name = "accion", defaultValue = "guardar") String accion,
            RedirectAttributes redirectAttributes) {
        try {
            productoService.guardar(dto);
            redirectAttributes.addFlashAttribute("exito", "Producto registrado correctamente");

            if ("nuevo".equals(accion)) {
                return "redirect:/productos/nuevo";
            } else {
                return "redirect:/inventario";
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al guardar");
            return "redirect:/productos/nuevo";
        }
    }

    @PostMapping("/baja/{id}")
    public String darDeBaja(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        try {
            productoService.darDeBaja(id);
            redirectAttributes.addFlashAttribute("exito", "Producto dado de baja");
            return "redirect:/inventario";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al dar de baja");
            return "redirect:/inventario";
        }
    }

    @PostMapping("/reactivar/{id}")
    public String reactivar(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        try {
            productoService.reactivar(id);
            redirectAttributes.addFlashAttribute("exito", "Producto reactivado");
            return "redirect:/inventario";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al reactivar ");
            return "redirect:/inventario";
        }
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable("id") Integer id, Model model) {
        Producto producto = productoService.buscarPorId(id); // O tu repositorio directo
        model.addAttribute("producto", producto);
        return "productos/nuevo";
    }

    @PostMapping("/actualizar")
    public String actualizarProducto(@ModelAttribute("producto") Producto producto, RedirectAttributes ra) {
        productoService.actualizar(producto);
        ra.addFlashAttribute("exito", "¡Producto actualizado correctamente!");
        return "redirect:/inventario";
    }
}
