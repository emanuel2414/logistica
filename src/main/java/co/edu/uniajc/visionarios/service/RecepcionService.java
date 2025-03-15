package co.edu.uniajc.visionarios.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import co.edu.uniajc.visionarios.model.*;
import co.edu.uniajc.visionarios.repository.RecepcionRepository;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class RecepcionService {

    @Autowired
    private RecepcionRepository recepcionRepository;

    @Autowired
    private EmailService emailService; // Servicio para enviar correos

    @Transactional
    public RecepcionModel crearRecepcion(RecepcionModel recepcion) {
        recepcion.setEstado(RecepcionModel.EstadoRecepcion.PENDIENTE);
        recepcion.setFechaRecepcion(LocalDateTime.now());
        
        // Crear historial de creación
        HistorialRecepcionModel historial = HistorialRecepcionModel.builder()
            .recepcion(recepcion)
            .fechaCambio(LocalDateTime.now())
            .usuario("SISTEMA")
            .descripcion("Creación de recepción")
            .tipoCambio(HistorialRecepcionModel.TipoCambio.CREACION)
            .build();
        
        recepcion.getHistorial().add(historial);
        return recepcionRepository.save(recepcion);
    }

    @Transactional(readOnly = true)
    public RecepcionModel consultarRecepcion(Long id) {
        return recepcionRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Recepción no encontrada"));
    }

    @Transactional
    public RecepcionModel actualizarRecepcion(Long id, RecepcionModel recepcionActualizada, String usuario) {
        RecepcionModel recepcion = consultarRecepcion(id);
        
        if (recepcion.getEstado() != RecepcionModel.EstadoRecepcion.PENDIENTE) {
            throw new RuntimeException("Solo se pueden modificar recepciones en estado PENDIENTE");
        }

        // Actualizar productos
        recepcion.setProductos(recepcionActualizada.getProductos());
        
        // Registrar historial
        HistorialRecepcionModel historial = HistorialRecepcionModel.builder()
            .recepcion(recepcion)
            .fechaCambio(LocalDateTime.now())
            .usuario(usuario)
            .descripcion("Actualización de productos")
            .tipoCambio(HistorialRecepcionModel.TipoCambio.MODIFICACION)
            .build();
        
        recepcion.getHistorial().add(historial);
        return recepcionRepository.save(recepcion);
    }

    @Transactional
    public void eliminarRecepcion(Long id, String usuario, String motivo) {
        RecepcionModel recepcion = consultarRecepcion(id);
        
        if (recepcion.getEstado() != RecepcionModel.EstadoRecepcion.PENDIENTE) {
            throw new RuntimeException("Solo se pueden eliminar recepciones en estado PENDIENTE");
        }

        // Registrar historial antes de eliminar
        HistorialRecepcionModel historial = HistorialRecepcionModel.builder()
            .recepcion(recepcion)
            .fechaCambio(LocalDateTime.now())
            .usuario(usuario)
            .descripcion("Eliminación de recepción. Motivo: " + motivo)
            .tipoCambio(HistorialRecepcionModel.TipoCambio.ELIMINACION)
            .build();
        
        recepcion.getHistorial().add(historial);
        recepcionRepository.save(recepcion); // Guardar historial
        
        // Notificar
        emailService.notificarEliminacionRecepcion(recepcion, usuario, motivo);
        
        recepcionRepository.delete(recepcion);
    }

    @Transactional
    public RecepcionModel confirmarRecepcion(Long id, String usuario) {
        RecepcionModel recepcion = consultarRecepcion(id);
        
        if (recepcion.getEstado() != RecepcionModel.EstadoRecepcion.PENDIENTE) {
            throw new RuntimeException("Solo se pueden confirmar recepciones en estado PENDIENTE");
        }

        recepcion.setEstado(RecepcionModel.EstadoRecepcion.CONFIRMADA);
        
        // Registrar historial
        HistorialRecepcionModel historial = HistorialRecepcionModel.builder()
            .recepcion(recepcion)
            .fechaCambio(LocalDateTime.now())
            .usuario(usuario)
            .descripcion("Confirmación de recepción")
            .tipoCambio(HistorialRecepcionModel.TipoCambio.CONFIRMACION)
            .build();
        
        recepcion.getHistorial().add(historial);
        
        // Actualizar inventario y notificar
        actualizarInventario(recepcion);
        emailService.notificarConfirmacionRecepcion(recepcion);
        
        return recepcionRepository.save(recepcion);
    }

    private void actualizarInventario(RecepcionModel recepcion) {
        // Lógica para actualizar el inventario
        // Este método debería integrarse con el sistema de inventario
    }
} 
