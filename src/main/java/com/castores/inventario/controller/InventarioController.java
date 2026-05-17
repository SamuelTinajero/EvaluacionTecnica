package com.castores.inventario.controller;

import com.castores.inventario.entity.Movimiento;
import com.castores.inventario.service.MovimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

@Controller
@RequestMapping("/movimientos")
public class InventarioController {

    @Autowired
    private MovimientoService movimientoService;

    @GetMapping
    public String listar(@RequestParam(required = false) String tipo, Model model) {
        List<Movimiento> movimientos;

        if (tipo != null && !tipo.isEmpty()) {
            movimientos = movimientoService.listarPorTipo(tipo);
            model.addAttribute("tipoSeleccionado", tipo);
        } else {
            movimientos = movimientoService.listarTodos();
            model.addAttribute("tipoSeleccionado", "");
        }

        model.addAttribute("movimientos", movimientos);
        return "movimientos/lista";
    }
}
