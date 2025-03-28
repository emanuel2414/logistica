package co.edu.uniajc.visionarios.model;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="transportistadespacho")
public class TransportistaDespachoModel {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="id_tran_desp")
  private Long id;

  @ManyToOne
  @JoinColumn(name="tran_documento", nullable = false)
  private TransportistaModel transportista;

  @OneToOne
  @JoinColumn(name="id_despacho", nullable = false)
  private DespachoModel despachoModel;
}
