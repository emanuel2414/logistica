package co.edu.uniajc.visionarios.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.uniajc.visionarios.model.TransportistaModel;
import co.edu.uniajc.visionarios.repository.TransportistaDespachoRepository;
import co.edu.uniajc.visionarios.repository.TransportistaRepository;

@Service
public class TransportistaService {
  
  @Autowired
  private TransportistaRepository transportistaRepository;
  @Autowired
  private TransportistaDespachoRepository transportistaDespachoRepository;


  public TransportistaModel guardar(TransportistaModel transportistaModel){
    return transportistaRepository.save(transportistaModel);
  }

  public TransportistaModel actualizar(Long id, TransportistaModel transportistaModel) {
    return transportistaRepository.findById(id).map(trasnportista -> {
      if(trasnportista.getEstado().equals("activo")){
        trasnportista.setDocumento(id);
        trasnportista.setCapacidadCarga(transportistaModel.getCapacidadCarga());
        trasnportista.setContacto(transportistaModel.getContacto());
        trasnportista.setEstado(transportistaModel.getEstado());
        trasnportista.setRazonSocial(transportistaModel.getRazonSocial());
        trasnportista.setTipoVehiculo(transportistaModel.getTipoVehiculo());
        return transportistaRepository.save(trasnportista);
      }else{
        throw new RuntimeException("El transportista no se puede actualizar porque su estado esta: " + trasnportista.getEstado());
      }
    }).orElseThrow(() -> {
        return new RuntimeException("Transportista no encontrado con id: " + id);
    });
  }

  public List<TransportistaModel> consultar(){
    return transportistaRepository.findAll();
  }

  public List<TransportistaModel> consultarEstado(String estado){
    return transportistaRepository.findByEstado(estado);
  }

  public List<TransportistaModel> consultarRazonSocial(String razonSocial){
    return transportistaRepository.findByRazonSocial(razonSocial);
  }

  public void eliminar(Long id){
    TransportistaModel transportistaModel = transportistaRepository.findById(id)
      .orElseThrow(() -> new RuntimeException("Transportista no encontrado con id: " + id));

    boolean tieneDespachos = transportistaDespachoRepository.existsByTransportista(transportistaModel);
    
    if (tieneDespachos) {
      throw new RuntimeException("No se puede eliminar. El transportista tiene despachos asignados.");
    }

    transportistaRepository.deleteById(id);
  }
}
