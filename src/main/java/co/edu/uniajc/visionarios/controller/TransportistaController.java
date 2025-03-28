package co.edu.uniajc.visionarios.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.uniajc.visionarios.model.TransportistaModel;
import co.edu.uniajc.visionarios.service.TransportistaService;
import io.swagger.v3.oas.annotations.Parameter;

@RestController
@RequestMapping("/transportista")
public class TransportistaController {

  private TransportistaService transportistaService;

  public TransportistaController(TransportistaService transportistaService) {
    this.transportistaService = transportistaService;
  }

  @PostMapping(path = "/registrar")
  public TransportistaModel crearTransportista(@RequestBody TransportistaModel transportistaModel){
    return transportistaService.guardar(transportistaModel);
  }

  @PutMapping("/modificar/{id}")
  public ResponseEntity<?> modificarTransportista(
    @PathVariable Long id,
    @RequestBody TransportistaModel transportistaModel) {

    try {
      TransportistaModel transportistaActualizado = transportistaService.actualizar(id, transportistaModel);
      return ResponseEntity.ok(transportistaActualizado);
    } catch (RuntimeException e) {
      return ResponseEntity.status(404).body(e.getMessage());  
    }
  }

  @GetMapping("/consultar")
  public ResponseEntity<?> consultarDespacho() {
    try {
      transportistaService.consultar();
      return ResponseEntity.ok(transportistaService.consultar());
    } catch (RuntimeException e) {
      return ResponseEntity.status(400).body(e.getMessage());
    }
  }

  @GetMapping("/estado")
  public ResponseEntity<?> consultarDespachoPorEstado(
    @RequestParam(name = "estado") 
    @Parameter(description = "Estado de los transportistas a consultar", example = "(activo/inactivo)")
    String estado) {
    try {
      List<TransportistaModel> transportista = transportistaService.consultarEstado(estado);
      return ResponseEntity.ok(transportista);
    } catch (RuntimeException e) {
      return ResponseEntity.status(400).body(e.getMessage());
    }
  }

  @GetMapping("/razonsocial")
  public ResponseEntity<?> consultarRazonSocial(
    @RequestParam(name = "razonSocial")
    String razonSocial) {
    try {
      List<TransportistaModel> transportista = transportistaService.consultarRazonSocial(razonSocial);
      return ResponseEntity.ok(transportista);
    } catch (RuntimeException e) {
      return ResponseEntity.status(400).body(e.getMessage());
    }
  }

  @DeleteMapping(path = "/eliminar")
  public ResponseEntity<?> eliminarTransportista(
    @RequestParam(name = "id") 
    @Parameter(description = "ID del transportista a eliminar", example = "123")
    Long id) {
    try {
      transportistaService.eliminar(id);
      return ResponseEntity.ok("Transportista eliminado correctamente.");
    } catch (RuntimeException e) {
      return ResponseEntity.status(400).body(e.getMessage());
    }
  }
}
