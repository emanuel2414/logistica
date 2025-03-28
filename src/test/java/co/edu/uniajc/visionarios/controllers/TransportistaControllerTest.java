package co.edu.uniajc.visionarios.controllers;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import co.edu.uniajc.visionarios.controller.TransportistaController;
import co.edu.uniajc.visionarios.model.TransportistaModel;
import co.edu.uniajc.visionarios.service.TransportistaService;
import com.fasterxml.jackson.databind.ObjectMapper;

class TransportistaControllerTest {

  private MockMvc mockMvc;

  @Mock
  private TransportistaService transportistaService;

  @InjectMocks
  private TransportistaController transportistaController;

  private ObjectMapper objectMapper = new ObjectMapper();

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    mockMvc = MockMvcBuilders.standaloneSetup(transportistaController).build();
  }

  @Test
  void testCrearTransportista() throws Exception {
    TransportistaModel transportista = TransportistaModel.builder()
      .documento(1L)
      .razonSocial("Juan Perez")
      .contacto(123456789)
      .tipoVehiculo("Camion")
      .capacidadCarga("10 toneladas")
      .estado("activo")
      .build();

    when(transportistaService.guardar(any(TransportistaModel.class))).thenReturn(transportista);

    mockMvc.perform(post("/transportista/registrar")
      .contentType(MediaType.APPLICATION_JSON)
      .content(objectMapper.writeValueAsString(transportista)))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.documento").value(1))
      .andExpect(jsonPath("$.razonSocial").value("Juan Perez"));
  }
}