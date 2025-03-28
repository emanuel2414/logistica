package co.edu.uniajc.visionarios.repository;

//import static org.junit.jupiter.api.Assertions.assertEquals;

//import java.util.List;

//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

//import co.edu.uniajc.visionarios.model.TransportistaModel;
//import jakarta.transaction.Transactional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
class TransportistaRepositoryTest {
    //@Autowired
    //private TransportistaRepository transportistaRepository;
//
    //@Test
    //@Transactional
    //@DisplayName("Guardar y buscar transportista por estado")
    //void guardarYBuscarPorEstado() {
    //  // Crear y guardar un transportista
    //  TransportistaModel transportista = new TransportistaModel(1L, "Transportes XYZ", 321654987, "Camión", "10 toneladas", "Activo");
    //  transportistaRepository.save(transportista);
//
    //  // Buscar transportistas por estado
    //  List<TransportistaModel> resultado = transportistaRepository.findByEstado("Activo");
//
    //  // Verificar que el transportista fue encontrado
    //  assertEquals(1, resultado.size());
    //  assertEquals("Transportes XYZ", resultado.get(0).getRazonSocial());
    //}
//
    //@Test
    //@Transactional
    //@DisplayName("Guardar y buscar transportista por razón social")
    //void guardarYBuscarPorRazonSocial() {
      // Crear y guardar un transportista
      //TransportistaModel transportista = new TransportistaModel(2L, "Transportes ABC", 987456123, "Furgoneta", "5 toneladas", "Inactivo");
      //transportistaRepository.save(transportista);
//
      //// Buscar transportistas por razón social
      //List<TransportistaModel> resultado = transportistaRepository.findByRazonSocial("Transportes ABC");
//
      //// Verificar que el transportista fue encontrado
      //assertEquals(1, resultado.size());
      //assertEquals("Inactivo", resultado.get(0).getEstado());
    //}
}
