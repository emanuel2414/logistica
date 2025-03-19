package co.edu.uniajc.visionarios.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "proveedor")

public class ProveedorModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_proveedor")
    private Long id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "nit", unique = true, nullable = false)
    private String nit;

    @Column(name = "contacto")
    private String contacto;

    @Column(name = "direccion")
    private String direccion;
} 