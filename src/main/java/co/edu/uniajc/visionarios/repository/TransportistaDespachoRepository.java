package co.edu.uniajc.visionarios.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.uniajc.visionarios.model.TransportistaDespachoModel;
import co.edu.uniajc.visionarios.model.TransportistaModel;

@Repository
public interface TransportistaDespachoRepository extends JpaRepository<TransportistaDespachoModel, Long> {
  boolean existsByTransportista(TransportistaModel transportistaModel);
}
