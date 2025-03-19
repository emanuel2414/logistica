package co.edu.uniajc.visionarios.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "recepcion")
public class RecepcionModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_recepcion")
    private Long id;

    @Column(name = "fecha_recepcion")
    private LocalDateTime fechaRecepcion;

    @Column(name = "estado")
    @Enumerated(EnumType.STRING)
    private EstadoRecepcion estado;

    @Column(name = "numero_orden_compra")
    private String numeroOrdenCompra;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_proveedor")
    private ProveedorModel proveedor;

    @OneToMany(mappedBy = "recepcion", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductoRecepcionModel> productos;

    @OneToMany(mappedBy = "recepcion", cascade = CascadeType.ALL)
    private List<HistorialRecepcionModel> historial;

    public enum EstadoRecepcion {
        PENDIENTE,
        CONFIRMADA,
        CANCELADA
    }
} 
