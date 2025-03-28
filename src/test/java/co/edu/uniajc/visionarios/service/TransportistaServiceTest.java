package co.edu.uniajc.visionarios.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import co.edu.uniajc.visionarios.model.TransportistaModel;
import co.edu.uniajc.visionarios.repository.TransportistaDespachoRepository;
import co.edu.uniajc.visionarios.repository.TransportistaRepository;

@ExtendWith(MockitoExtension.class)
class TransportistaServiceTest {

  @Mock
  private TransportistaRepository transportistaRepository;

  @Mock
  private TransportistaDespachoRepository transportistaDespachoRepository;

  @InjectMocks
  private TransportistaService transportistaService;

  private TransportistaModel transportista;

  @BeforeEach
  void setUp() {
    transportista = new TransportistaModel(123456L, "Transportes XYZ", 321654987, "Camión", "10 toneladas", "activo");
  }

  @Test
  void probarGuardar() {
    when(transportistaRepository.save(transportista)).thenReturn(transportista);
    TransportistaModel resultado = transportistaService.guardar(transportista);
    assertEquals(transportista, resultado);
  }
}