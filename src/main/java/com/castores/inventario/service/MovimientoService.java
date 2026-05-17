package com.castores.inventario.service;

import com.castores.inventario.entity.Movimiento;
import com.castores.inventario.repository.MovimientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovimientoService {

    @Autowired
    private MovimientoRepository movimientoRepository;

    public List<Movimiento> listarTodos() {
        return movimientoRepository.findAllByOrderByFechaMovimientoDesc();
    }

    public List<Movimiento> listarPorTipo(String tipo) {
        List<Movimiento> todos = listarTodos();
        return todos.stream()
                .filter(m -> m.getTipoMovimiento().equalsIgnoreCase(tipo))
                .collect(Collectors.toList());
    }
}
