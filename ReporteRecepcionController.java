package co.edu.uniajc.visionarios.controller;

import co.edu.uniajc.visionarios.model.ReporteRecepcion;
import co.edu.uniajc.visionarios.service.ReporteRecepcionService;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;

 import java.util.List;



 @Controller
 @RequestMapping("/reportes")
 public class ReporteRecepcionController {
    private final ReporteRecepcionService service;

    public ReporteRecepcionController(ReporteRecepcionService service) {
        this.service = service;
    }
    
    @GetMapping
    @ResponseBody
    public List<ReporteRecepcion> obtenerReportes() {
       return service.obtenerTodos();
    }

    @PostMapping
    public ReporteRecepcion crearReporte(@RequestBody ReporteRecepcion reporte) {
        return service.guardar(reporte);
    }

    @GetMapping("/vista")
    public String mostrarReportes(Model model) {
        List<ReporteRecepcion> reportes = service.obtenerTodos(); // Obtener datos
        model.addAttribute("reportes", reportes); // Pasar datos a la vista
        return "reportes"; // Nombre del archivo HTML (sin .html)
    }

 @GetMapping("/{id}")
 @ResponseBody
 public ReporteRecepcion obtenerReportePorId(@PathVariable Long id) {
    return service.obtenerPorId(id);
 }

 @PutMapping("/{id}")
 @ResponseBody
 public ReporteRecepcion actualizarReporte(@PathVariable Long id, @RequestBody ReporteRecepcion reporte) {
    return service.actualizar(id, reporte);
 }

 @DeleteMapping("/{id}")
 @ResponseBody
 public void eliminarReporte(@PathVariable Long id) {
    service.eliminar(id);
 }



 }