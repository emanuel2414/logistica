package co.edu.uniajc.visionarios.model;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "producto_recepcion")

public class ProductoRecepcionModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto_recepcion")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_recepcion")
    private RecepcionModel recepcion;

    @Column(name = "id_producto")
    private Long idProducto;

    @Column(name = "nombre_producto")
    private String nombreProducto;

    @Column(name = "cantidad")
    private Double cantidad;

    @Column(name = "unidad_medida")
    private String unidadMedida;

    @Column(name = "estado_producto")
    @Enumerated(EnumType.STRING)
    private EstadoProducto estadoProducto;

    public enum EstadoProducto {
        ACEPTADO,
        RECHAZADO,
        PENDIENTE
    }
} 
