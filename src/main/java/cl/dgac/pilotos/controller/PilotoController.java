package cl.dgac.pilotos.controller;

import cl.dgac.pilotos.dto.PilotoRequestDTO;
import cl.dgac.pilotos.dto.PilotoResponseDTO;
import cl.dgac.pilotos.service.PilotoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pilotos")
public class PilotoController {

    private final PilotoService pilotoService;

    public PilotoController(PilotoService pilotoService) {
        this.pilotoService = pilotoService;
    }

    @GetMapping
    public ResponseEntity<List<PilotoResponseDTO>> listarPilotos() {
        return ResponseEntity.ok(pilotoService.listarPilotos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PilotoResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(pilotoService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<PilotoResponseDTO> crearPiloto(@Valid @RequestBody PilotoRequestDTO dto) {
        PilotoResponseDTO pilotoCreado = pilotoService.crearPiloto(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(pilotoCreado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PilotoResponseDTO> actualizarPiloto(
            @PathVariable Long id,
            @Valid @RequestBody PilotoRequestDTO dto) {

        return ResponseEntity.ok(pilotoService.actualizarPiloto(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPiloto(@PathVariable Long id) {
        pilotoService.eliminarPiloto(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/buscar-licencia")
    public ResponseEntity<PilotoResponseDTO> buscarPorLicencia(
            @RequestParam String numeroLicencia) {

        return ResponseEntity.ok(pilotoService.buscarPorLicencia(numeroLicencia));
    }

    @GetMapping("/estado")
    public ResponseEntity<List<PilotoResponseDTO>> listarPorEstado(
            @RequestParam Boolean activo) {

        return ResponseEntity.ok(pilotoService.listarPorEstado(activo));
    }

    @GetMapping("/buscar-apellido")
    public ResponseEntity<List<PilotoResponseDTO>> buscarPorApellido(
            @RequestParam String apellido) {

        return ResponseEntity.ok(pilotoService.buscarPorApellido(apellido));
    }

    @GetMapping("/usuarios")
    public ResponseEntity<String> consultarUsuarios() {
        return ResponseEntity.ok(pilotoService.consultarMicroservicioUsuarios());
    }
}