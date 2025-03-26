package co.edu.uniajc.visionarios.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.uniajc.visionarios.model.DespachoModel;

@Repository
public interface DespachoRepository extends JpaRepository<DespachoModel, Long> {
  List<DespachoModel> findByEstado(String estado);
}
