package com.castores.inventario.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

@Entity
@Table(name = "productos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idProducto")
    private Integer idProducto;

    @Column(name = "nombre", length = 100, nullable = false)
    private String nombre;

    @Column(name = "descripcion", length = 255)
    private String descripcion;

    @Column(name = "stock", nullable = false)
    private Integer stock = 0;

    @Column(name = "estatus", nullable = false)
    private Integer estatus = 1;

    @Column(name = "fechaCreacion", nullable = false, columnDefinition = "DATETIME DEFAULT GETDATE()")
    private LocalDateTime fechaCreacion;

    @Column(name = "fechaActualizacion")
    private LocalDateTime fechaActualizacion;

    @CreatedBy
    @Column(name = "creadoPor", updatable = false)
    private String creadoPor;

    @LastModifiedBy
    @Column(name = "modificadoPor")
    private String modificadoPor;

    @PrePersist
    protected void onCreate() {
        if (fechaCreacion == null) {
            fechaCreacion = LocalDateTime.now();
        }
    }
}
