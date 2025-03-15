package co.edu.uniajc.visionarios.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;


@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "historial_recepcion")


@Table(name = "historial_recepcion")
public class HistorialRecepcionModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_historial")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_recepcion")
    private RecepcionModel recepcion;

    @Column(name = "fecha_cambio")
    private LocalDateTime fechaCambio;

    @Column(name = "usuario")
    private String usuario;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "tipo_cambio")
    @Enumerated(EnumType.STRING)
    private TipoCambio tipoCambio;

    public enum TipoCambio {
        CREACION,
        MODIFICACION,
        ELIMINACION,
        CONFIRMACION
    }
} 