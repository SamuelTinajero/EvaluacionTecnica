package com.castores.inventario.repository;

import com.castores.inventario.entity.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MovimientoRepository extends JpaRepository<Movimiento, Integer> {
    List<Movimiento> findByProductoIdProductoOrderByFechaMovimientoDesc(Integer idProducto);
    List<Movimiento> findAllByOrderByFechaMovimientoDesc();
}
