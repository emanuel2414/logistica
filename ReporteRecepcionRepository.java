package co.edu.uniajc.visionarios.repository;

import co.edu.uniajc.visionarios.model.ReporteRecepcion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReporteRecepcionRepository extends JpaRepository<ReporteRecepcion, Long> {
}