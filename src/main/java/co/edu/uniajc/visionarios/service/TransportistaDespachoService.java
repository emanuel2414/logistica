package co.edu.uniajc.visionarios.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.uniajc.visionarios.model.DespachoModel;
import co.edu.uniajc.visionarios.model.TransportistaDespachoModel;
import co.edu.uniajc.visionarios.model.TransportistaModel;
import co.edu.uniajc.visionarios.repository.DespachoRepository;
import co.edu.uniajc.visionarios.repository.TransportistaDespachoRepository;
import co.edu.uniajc.visionarios.repository.TransportistaRepository;

@Service
public class TransportistaDespachoService {

    @Autowired
    private TransportistaDespachoRepository transportistaDespachoRepository;

    @Autowired
    private TransportistaRepository transportistaRepository;

    @Autowired
    private DespachoRepository despachoRepository;

    public TransportistaDespachoModel asignarTransportista(Long idDespacho, Long idTransportista) {
      DespachoModel despacho = despachoRepository.findById(idDespacho)
        .orElseThrow(() -> new RuntimeException("Despacho no encontrado"));

      TransportistaModel transportista = transportistaRepository.findById(idTransportista)
        .orElseThrow(() -> new RuntimeException("Transportista no encontrado"));

      TransportistaDespachoModel transportistaDespacho = new TransportistaDespachoModel();
      transportistaDespacho.setDespachoModel(despacho);
      transportistaDespacho.setTransportista(transportista);

      despacho.setEstado("En transito");
      despachoRepository.save(despacho);

      return transportistaDespachoRepository.save(transportistaDespacho);
    }
}
