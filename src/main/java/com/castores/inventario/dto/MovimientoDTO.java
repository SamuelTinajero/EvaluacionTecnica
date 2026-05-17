package com.castores.inventario.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovimientoDTO {
    private Integer idMovimiento;
    private Integer idProducto;
    private Integer idUsuario;
    private String tipoMovimiento;
    private Integer cantidad;
    private Integer stockAnterior;
    private Integer stockNuevo;
    private LocalDateTime fechaMovimiento;
}
