package co.edu.uniajc.visionarios.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class TransportistaModelTest {
  @Test
  void probarGettersYSetters() {
    // Crear un objeto TransportistaModel
    TransportistaModel transportista = new TransportistaModel();
    
    // Asignar valores a los atributos
    transportista.setDocumento(123456789L);
    transportista.setRazonSocial("Transportes XYZ");
    transportista.setContacto(321654987);
    transportista.setTipoVehiculo("Camión");
    transportista.setCapacidadCarga("10 toneladas");
    transportista.setEstado("Activo");
    
    // Verificar que los valores sean correctos
    assertEquals(123456789L, transportista.getDocumento());
    assertEquals("Transportes XYZ", transportista.getRazonSocial());
    assertEquals(321654987, transportista.getContacto());
    assertEquals("Camión", transportista.getTipoVehiculo());
    assertEquals("10 toneladas", transportista.getCapacidadCarga());
    assertEquals("Activo", transportista.getEstado());
  }
}
