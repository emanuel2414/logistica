package co.edu.uniajc.visionarios.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "reportes_recepcion")
public class ReporteRecepcion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "codigo_reporte", length = 16, nullable = false)
    private String codigoReporte;
    
    @Column(name = "proveedor", length = 30, nullable = false)
    private String proveedor;
    
    @Column(name = "fecha_hora_recepcion", nullable = false)
    private LocalDateTime fechaHoraRecepcion;
    
    @Column(name = "producto", length = 30, nullable = false)
    private String producto;
    
    @Column(name = "cantidad", nullable = false)
    private int cantidad;
    
    @Column(name = "estado", length = 15, nullable = false)
    private String estado;

    // Getters y Setters

    public Long getId() {
        return id;
    }

    public String getCodigoReporte() {
        return codigoReporte;
    }

    public String getProveedor() {
        return proveedor;
    }

    public LocalDateTime getFechaHoraRecepcion() {
        return fechaHoraRecepcion;
    }

    public String getProducto() {
        return producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public String getEstado() {
        return estado;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCodigoReporte(String codigoReporte) {
        this.codigoReporte = codigoReporte;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public void setFechaHoraRecepcion(LocalDateTime fechaHoraRecepcion) {
        this.fechaHoraRecepcion = fechaHoraRecepcion;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    
    
}

