package co.edu.uniajc.visionarios.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.uniajc.visionarios.model.DespachoModel;
import co.edu.uniajc.visionarios.model.ProductoDespachoModel;
import co.edu.uniajc.visionarios.repository.DespachoRepository;

@Service
public class DespachoService {

  @Autowired
  private DespachoRepository despachoRepository;

  public DespachoModel guardar(DespachoModel despachoModel){

    despachoModel.setEstado("En preparacion");

    for(ProductoDespachoModel producto : despachoModel.getProductos()){
      producto.setDespacho(despachoModel);
    }

    return despachoRepository.save(despachoModel);
  }
  
  public DespachoModel actualizar(Long id, DespachoModel despachoModel) {
    return despachoRepository.findById(id).map(despacho -> {
      if(despacho.getEstado().equals("En preparacion")){
        despacho.setCodigoSeguimiento(despachoModel.getCodigoSeguimiento());
        despacho.setNombreCompletoCliente(despachoModel.getNombreCompletoCliente());
        despacho.setDocumentoCliente(despachoModel.getDocumentoCliente());
        despacho.setDireccionEntrega(despachoModel.getDireccionEntrega());
        despacho.setContacto(despachoModel.getContacto());
        return despachoRepository.save(despacho);
      }else{
        throw new RuntimeException("El despacho no se puede actualizar porque su estado esta: " + despacho.getEstado());
      }
    }).orElseThrow(() -> {
        return new RuntimeException("Despacho no encontrado con id: " + id);
    });
  }
//Actualización despacho
  public DespachoModel actualizarEstadoDespacho(Long id, DespachoModel despachoModel, String estado) {
    return despachoRepository.findById(id).map(despacho -> {
      if(despacho.getEstado().equals("En preparacion")){
       despacho.setEstado(estado);
        return despachoRepository.save(despacho);
      }else{
        throw new RuntimeException("El despacho no se puede actualizar porque su estado esta: " + despacho.getEstado());
      }
    }).orElseThrow(() -> {
        return new RuntimeException("Despacho no encontrado con id: " + id);
    });
  }

  public List<DespachoModel> consultar(){
    return despachoRepository.findAll();
  }

  public List<DespachoModel> consultarEstado(String estado){
    return despachoRepository.findByEstado(estado);
  }

  public void eliminar(Long id){
    DespachoModel despachoModel = despachoRepository.findById(id)
    .orElseThrow(() -> new RuntimeException("Despacho no encontrado con id: " + id));

    if(!"En preparacion".equalsIgnoreCase(despachoModel.getEstado())){
      throw new RuntimeException("No se puede eliminar. Solo se pueden eliminar despachos en estado 'En preparación'.");
    }

    despachoRepository.deleteById(id);
  }
}
