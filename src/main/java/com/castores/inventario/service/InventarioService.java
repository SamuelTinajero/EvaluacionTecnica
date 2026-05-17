package com.castores.inventario.service;

import com.castores.inventario.entity.Producto;
import com.castores.inventario.entity.Movimiento;
import com.castores.inventario.entity.Usuario;
import com.castores.inventario.repository.ProductoRepository;
import com.castores.inventario.repository.MovimientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;

@Service
public class InventarioService {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private MovimientoRepository movimientoRepository;

    @Transactional
    public Movimiento entrada(Integer idProducto, Integer cantidad, Usuario usuario) {
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor a cero");
        }

        Producto producto = productoRepository.findById(idProducto)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        if (producto.getEstatus() == 0) {
            throw new IllegalArgumentException(
                    "No se puede registrar entrada: El artículo '" + producto.getNombre() + "' está dado de baja.");
        }

        Integer stockAnterior = producto.getStock();

        producto.setStock(producto.getStock() + cantidad);
        productoRepository.save(producto);

        Movimiento movimiento = Movimiento.builder()
                .producto(producto)
                .usuario(usuario)
                .tipoMovimiento("ENTRADA")
                .cantidad(cantidad)
                .stockAnterior(stockAnterior)
                .stockNuevo(producto.getStock())
                .fechaMovimiento(LocalDateTime.now())
                .build();

        return movimientoRepository.save(movimiento);
    }

    @Transactional
    public Movimiento salida(Integer idProducto, Integer cantidad, Usuario usuario) {
        // Obtener producto
        Producto producto = productoRepository.findById(idProducto)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        // Validación 1: Producto debe estar activo
        if (producto.getEstatus() == 0) {
            throw new IllegalArgumentException("El producto está inactivo");
        }

        // Validación 2: Cantidad debe ser mayor a cero
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor a cero");
        }

        // Validación 3: Stock suficiente
        if (cantidad > producto.getStock()) {
            throw new IllegalArgumentException("Stock insuficiente. Stock actual: " + producto.getStock());
        }

        // Registrar stock anterior
        Integer stockAnterior = producto.getStock();

        // Restar del stock
        producto.setStock(producto.getStock() - cantidad);
        productoRepository.save(producto);

        // Crear movimiento
        Movimiento movimiento = Movimiento.builder()
                .producto(producto)
                .usuario(usuario)
                .tipoMovimiento("SALIDA")
                .cantidad(cantidad)
                .stockAnterior(stockAnterior)
                .stockNuevo(producto.getStock())
                .fechaMovimiento(LocalDateTime.now())
                .build();

        return movimientoRepository.save(movimiento);
    }
}
