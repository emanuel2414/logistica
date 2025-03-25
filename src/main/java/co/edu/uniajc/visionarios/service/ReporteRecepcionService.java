package co.edu.uniajc.visionarios.service;

import co.edu.uniajc.visionarios.model.ReporteRecepcion;
import co.edu.uniajc.visionarios.repository.ReporteRecepcionRepository;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ReporteRecepcionService {
    private final ReporteRecepcionRepository repository;

    public ReporteRecepcionService(ReporteRecepcionRepository repository) {
        this.repository = repository;
    }

    public List<ReporteRecepcion> obtenerTodos() {
        return repository.findAll();
    }

    public ReporteRecepcion guardar(ReporteRecepcion reporte) {
        return repository.save(reporte);
    }

    public ReporteRecepcion actualizar(Long id, ReporteRecepcion nuevoReporte) {
    ReporteRecepcion existente = repository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Reporte no encontrado con id: " + id));
    
    existente.setCodigoReporte(nuevoReporte.getCodigoReporte());
    existente.setProveedor(nuevoReporte.getProveedor());
    existente.setFechaHoraRecepcion(nuevoReporte.getFechaHoraRecepcion());
    existente.setProducto(nuevoReporte.getProducto());
    existente.setCantidad(nuevoReporte.getCantidad());
    existente.setEstado(nuevoReporte.getEstado());
    
    return repository.save(existente);
}

public void eliminar(Long id) {
    ReporteRecepcion existente = repository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Reporte no encontrado con id: " + id));
    
    repository.delete(existente);
}

    public ReporteRecepcion obtenerPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reporte no encontrado con id: " + id));
    }
    
    
}