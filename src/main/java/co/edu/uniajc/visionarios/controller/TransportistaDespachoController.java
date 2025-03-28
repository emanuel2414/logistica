package co.edu.uniajc.visionarios.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.uniajc.visionarios.model.TransportistaDespachoModel;
import co.edu.uniajc.visionarios.service.TransportistaDespachoService;
import io.swagger.v3.oas.annotations.Parameter;

@RestController
@RequestMapping("/transportista_despacho")
public class TransportistaDespachoController {

  private final TransportistaDespachoService transportistaDespachoService;

  public TransportistaDespachoController(TransportistaDespachoService transportistaDespachoService) {
    this.transportistaDespachoService = transportistaDespachoService;
  }

  @PostMapping("/asignar")
  public ResponseEntity<?> asignarTransportista(
    @RequestParam 
    @Parameter(description = "ID del despacho", example = "123")
    Long idDespacho, 
    @RequestParam 
    @Parameter(description = "Documento del transportista", example = "123")
    Long idTransportista) {
      
    try {
      TransportistaDespachoModel asignacion = transportistaDespachoService.asignarTransportista(idDespacho, idTransportista);

      Map<String, Object> respuesta = new HashMap<>();
      respuesta.put("nombreCompletoCliente", asignacion.getDespachoModel().getNombreCompletoCliente());
      respuesta.put("direccionEntrega", asignacion.getDespachoModel().getDireccionEntrega());
      respuesta.put("contactoCliente", asignacion.getDespachoModel().getContacto());
      respuesta.put("codigoSeguimiento", asignacion.getDespachoModel().getCodigoSeguimiento());
      respuesta.put("razonSocial", asignacion.getTransportista().getRazonSocial());
      respuesta.put("contactoTransportista", asignacion.getTransportista().getContacto());
      return ResponseEntity.ok(respuesta);
    } catch (RuntimeException e) {
      return ResponseEntity.status(400).body(e.getMessage());
    }
  }
}
