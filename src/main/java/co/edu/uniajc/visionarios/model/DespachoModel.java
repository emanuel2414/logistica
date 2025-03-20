package co.edu.uniajc.visionarios.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
//Emanuel Barbosa Labrada
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="despacho")
public class DespachoModel {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="id_despacho")
  private Long id;

  @Column(name="cod_seguimiento")
  private String codigoSeguimiento;

  @Column(name="clie_nombre_completo")
  private String nombreCompletoCliente;

  @Column(name="clie_documento")
  private int documentoCliente;

  @Column(name="clie_direccion_entrega")
  private String direccionEntrega;

  @Column(name="clie_contacto")
  private int contacto;

  @Column(name="desp_estado")
  private String estado;

  // Relación con productos del despacho (1:N)
  @OneToMany(mappedBy = "despacho", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<ProductoDespachoModel> productos;

  //Eres como un dia de escuela. Sin clase, sin estido!.
  //No se
}
