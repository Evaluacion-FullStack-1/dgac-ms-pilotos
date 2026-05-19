package cl.dgac.pilotos.service;

import cl.dgac.pilotos.dto.PilotoRequestDTO;
import cl.dgac.pilotos.dto.PilotoResponseDTO;
import cl.dgac.pilotos.exception.ResourceNotFoundException;
import cl.dgac.pilotos.mapper.PilotoMapper;
import cl.dgac.pilotos.model.Piloto;
import cl.dgac.pilotos.repository.PilotoRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PilotoService {

    private final PilotoRepository pilotoRepository;
    private final PilotoMapper pilotoMapper;
    private final WebClient.Builder webClientBuilder;

    public PilotoService(PilotoRepository pilotoRepository,
                         PilotoMapper pilotoMapper,
                         WebClient.Builder webClientBuilder) {
        this.pilotoRepository = pilotoRepository;
        this.pilotoMapper = pilotoMapper;
        this.webClientBuilder = webClientBuilder;
    }

    public List<PilotoResponseDTO> listarPilotos() {
        return pilotoRepository.findAll()
                .stream()
                .map(pilotoMapper::toDTO)
                .collect(Collectors.toList());
    }

    public PilotoResponseDTO buscarPorId(Long id) {
        Piloto piloto = pilotoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Piloto no encontrado con ID: " + id));

        return pilotoMapper.toDTO(piloto);
    }

    public PilotoResponseDTO crearPiloto(PilotoRequestDTO dto) {
        Piloto piloto = pilotoMapper.toEntity(dto);
        Piloto pilotoGuardado = pilotoRepository.save(piloto);

        return pilotoMapper.toDTO(pilotoGuardado);
    }

    public PilotoResponseDTO actualizarPiloto(Long id, PilotoRequestDTO dto) {
        Piloto piloto = pilotoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Piloto no encontrado con ID: " + id));

        pilotoMapper.updateEntity(piloto, dto);
        Piloto pilotoActualizado = pilotoRepository.save(piloto);

        return pilotoMapper.toDTO(pilotoActualizado);
    }

    public void eliminarPiloto(Long id) {
        Piloto piloto = pilotoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Piloto no encontrado con ID: " + id));

        pilotoRepository.delete(piloto);
    }

    public PilotoResponseDTO buscarPorLicencia(String numeroLicencia) {
        Piloto piloto = pilotoRepository.findByNumeroLicencia(numeroLicencia)
                .orElseThrow(() -> new ResourceNotFoundException("Piloto no encontrado con licencia: " + numeroLicencia));

        return pilotoMapper.toDTO(piloto);
    }

    public List<PilotoResponseDTO> listarPorEstado(Boolean activo) {
        return pilotoRepository.findByActivo(activo)
                .stream()
                .map(pilotoMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<PilotoResponseDTO> buscarPorApellido(String apellido) {
        return pilotoRepository.buscarPorApellido(apellido)
                .stream()
                .map(pilotoMapper::toDTO)
                .collect(Collectors.toList());
    }

    public String consultarMicroservicioUsuarios() {
        return webClientBuilder.build()
                .get()
                .uri("http://localhost:8081/api/usuarios")
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}