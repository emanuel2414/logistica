package co.edu.uniajc.visionarios.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.uniajc.visionarios.model.ProductoDespachoModel;

@Repository
public interface ProductoDespachoRepository extends JpaRepository<ProductoDespachoModel, Long> {
  
}
