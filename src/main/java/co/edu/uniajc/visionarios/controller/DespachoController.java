package co.edu.uniajc.visionarios.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.uniajc.visionarios.model.DespachoModel;
import co.edu.uniajc.visionarios.service.DespachoService;
import io.swagger.v3.oas.annotations.Parameter;

import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/despacho_productos")
public class DespachoController {

  @Autowired
  private DespachoService despachoService;

  @PostMapping(path = "/crear")
  public DespachoModel crearDespacho(@RequestBody DespachoModel despachoModel){
    return despachoService.guardar(despachoModel);
  }

  @PutMapping("/modificar")
  public ResponseEntity<?> modificarDespacho(
    @PathVariable Long id,
    @RequestBody DespachoModel despachoModel) {

    try {
      DespachoModel despachoActualizado = despachoService.actualizar(id, despachoModel);
      return ResponseEntity.ok(despachoActualizado);
    } catch (RuntimeException e) {
      return ResponseEntity.status(404).body(e.getMessage());  
    }
  }

  @GetMapping("/consultar")
  public ResponseEntity<?> consultarDespacho() {
    try {
      despachoService.consultar();
      return ResponseEntity.ok(despachoService.consultar());
    } catch (RuntimeException e) {
      return ResponseEntity.status(400).body(e.getMessage());
    }
  }

  @GetMapping("/estado")
  public ResponseEntity<?> consultarDespachoPorEstado(
    @RequestParam(name = "estado") 
    @Parameter(description = "Estado de los despachos a consultar", example = "(En preparacion/En transito/Entregado)")
    String estado) {
    try {
      List<DespachoModel> despachos = despachoService.consultarEstado(estado);
      return ResponseEntity.ok(despachos);
    } catch (RuntimeException e) {
      return ResponseEntity.status(400).body(e.getMessage());
    }
  }
  
  @DeleteMapping(path = "/eliminar")
  public ResponseEntity<?> eliminarDespacho(
    @RequestParam(name = "id") 
    @Parameter(description = "ID del despacho a eliminar", example = "123")
    Long id) {
    try {
      despachoService.eliminar(id);
      return ResponseEntity.ok("Despacho eliminado correctamente.");
    } catch (RuntimeException e) {
      return ResponseEntity.status(400).body(e.getMessage());
    }
  }
}
