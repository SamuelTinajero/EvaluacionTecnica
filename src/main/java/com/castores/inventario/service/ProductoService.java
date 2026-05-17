package com.castores.inventario.service;

import com.castores.inventario.dto.ProductoDTO;
import com.castores.inventario.entity.Producto;
import com.castores.inventario.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> listarTodos() {
        return productoRepository.findAll();
    }

    public List<Producto> listarActivos() {
        return productoRepository.findByEstatusOrderByNombre(1);
    }

    public Producto guardar(ProductoDTO dto) {
        String usuarioActual = SecurityContextHolder.getContext().getAuthentication().getName();
        Producto producto = Producto.builder()
                .nombre(dto.getNombre())
                .descripcion(dto.getDescripcion())
                .stock(0)
                .estatus(1)
                .fechaCreacion(LocalDateTime.now())
                .creadoPor(usuarioActual)
                .build();
        return productoRepository.save(producto);
    }

    public Producto darDeBaja(Integer id) {
        Producto producto = buscarPorId(id);
        producto.setEstatus(0);
        return productoRepository.save(producto);
    }

    public Producto reactivar(Integer id) {
        Producto producto = buscarPorId(id);
        producto.setEstatus(1);
        return productoRepository.save(producto);
    }

    public Producto buscarPorId(Integer id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + id));
    }

    public Producto actualizar(Producto producto) {
        String usuarioActual = SecurityContextHolder.getContext().getAuthentication().getName();
        producto.setModificadoPor(usuarioActual);
        producto.setFechaActualizacion(LocalDateTime.now());
        return productoRepository.save(producto);
    }
}
