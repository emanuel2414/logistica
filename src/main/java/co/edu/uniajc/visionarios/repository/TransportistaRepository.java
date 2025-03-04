package co.edu.uniajc.visionarios.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.uniajc.visionarios.model.TransportistaModel;

@Repository
public interface TransportistaRepository extends JpaRepository<TransportistaModel, Long> {
  List<TransportistaModel> findByEstado(String estado);
}
