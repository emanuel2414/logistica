package co.edu.uniajc.visionarios.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import co.edu.uniajc.visionarios.model.RecepcionModel;
import co.edu.uniajc.visionarios.model.RecepcionModel.EstadoRecepcion;
import java.util.List;

@Repository
public interface RecepcionRepository extends JpaRepository<RecepcionModel, Long> {
    List<RecepcionModel> findByEstado(EstadoRecepcion estado);
    List<RecepcionModel> findByProveedorId(Long proveedorId);
    boolean existsByNumeroOrdenCompra(String numeroOrdenCompra);
} 
