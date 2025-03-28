package co.edu.uniajc.visionarios;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import co.edu.uniajc.visionarios.model.TransportistaModel;
import co.edu.uniajc.visionarios.repository.TransportistaRepository;
import co.edu.uniajc.visionarios.service.TransportistaService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;

@ExtendWith(MockitoExtension.class)
public class TransportistaTest {

    @Mock
    private TransportistaRepository transportistaRepository;

    @InjectMocks
    private TransportistaService transportistaService;

    @Test
    void registrarTransportista(){
        TransportistaModel transportista = new TransportistaModel();
        transportista.setDocumento(1234567L);
        transportista.setCapacidadCarga("5000");
        transportista.setContacto(123);
        transportista.setEstado("activo");
        transportista.setRazonSocial("Transporte Seguro S.A.");
        transportista.setTipoVehiculo("Camion");

        when(transportistaRepository.save(transportista)).thenReturn(transportista);

        
        TransportistaModel resultado = transportistaService.guardar(transportista);
        assertNotNull(resultado);
        assertEquals(1234567L, resultado.getDocumento());
    }
}
