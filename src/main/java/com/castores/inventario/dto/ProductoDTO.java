package com.castores.inventario.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductoDTO {
    private Integer idProducto;
    private String nombre;
    private String descripcion;
    private Integer stock;
    private Integer estatus;
}
