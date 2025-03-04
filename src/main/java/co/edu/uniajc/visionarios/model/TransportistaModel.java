package co.edu.uniajc.visionarios.model;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="transportista")
public class TransportistaModel {
  @Id
  @Column(name="tran_documento")
  private Long documento;

  @Column(name="tran_razon_social")
  private String razonSocial;

  @Column(name="tran_contacto")
  private int contacto;

  @Column(name="tran_tipo_vehiculo")
  private String tipoVehiculo;

  @Column(name="tran_capacidad_carga")
  private String capacidadCarga;

  @Column(name="tran_estado")
  private String estado;
}
