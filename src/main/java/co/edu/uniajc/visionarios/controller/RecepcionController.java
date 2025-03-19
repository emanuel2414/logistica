package co.edu.uniajc.visionarios.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import co.edu.uniajc.visionarios.model.RecepcionModel;
import co.edu.uniajc.visionarios.service.RecepcionService;

@RestController
@RequestMapping("/api/recepciones")
public class RecepcionController {

    @Autowired
    private RecepcionService recepcionService;

    @PostMapping
    public ResponseEntity<RecepcionModel> crearRecepcion(@RequestBody RecepcionModel recepcion) {
        try {
            RecepcionModel nuevaRecepcion = recepcionService.crearRecepcion(recepcion);
            return ResponseEntity.ok(nuevaRecepcion);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecepcionModel> consultarRecepcion(@PathVariable Long id) {
        try {
            RecepcionModel recepcion = recepcionService.consultarRecepcion(id);
            return ResponseEntity.ok(recepcion);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<RecepcionModel> actualizarRecepcion(
            @PathVariable Long id,
            @RequestBody RecepcionModel recepcion,
            @RequestHeader("Usuario") String usuario) {
        try {
            RecepcionModel recepcionActualizada = recepcionService.actualizarRecepcion(id, recepcion, usuario);
            return ResponseEntity.ok(recepcionActualizada);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarRecepcion(
            @PathVariable Long id,
            @RequestHeader("Usuario") String usuario,
            @RequestParam String motivo) {
        try {
            recepcionService.eliminarRecepcion(id, usuario, motivo);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/{id}/confirmar")
    public ResponseEntity<RecepcionModel> confirmarRecepcion(
            @PathVariable Long id,
            @RequestHeader("Usuario") String usuario) {
        try {
            RecepcionModel recepcionConfirmada = recepcionService.confirmarRecepcion(id, usuario);
            return ResponseEntity.ok(recepcionConfirmada);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
} 