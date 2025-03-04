package co.edu.uniajc.visionarios.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="productodespacho")
public class ProductoDespachoModel {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="id_producto")
  private Long id;

  @Column(name="prod_codigo")
  private String codigoProducto;
  
  @Column(name="prod_cantidad")
  private int cantidad;

  @Column(name="prod_descripcion")
  private String descripcion;

  // Relación con Despacho (Muchos a Uno)
  @ManyToOne
  @JoinColumn(name = "id_despacho", nullable = false)
  @JsonIgnore
  private DespachoModel despacho;
}
