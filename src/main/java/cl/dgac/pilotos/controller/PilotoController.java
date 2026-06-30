package cl.dgac.pilotos.controller;

import cl.dgac.pilotos.dto.PilotoRequestDTO;
import cl.dgac.pilotos.dto.PilotoResponseDTO;
import cl.dgac.pilotos.service.PilotoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pilotos")
@Tag(name = "Pilotos", description = "Operaciones relacionadas con la gestión de pilotos del sistema DGAC")
public class PilotoController {

    private final PilotoService pilotoService;

    public PilotoController(PilotoService pilotoService) {
        this.pilotoService = pilotoService;
    }

    @Operation(summary = "Listar todos los pilotos", description = "Obtiene una lista completa de todos los pilotos registrados en el sistema.")
    @ApiResponse(responseCode = "200", description = "Lista de pilotos obtenida exitosamente")
    @GetMapping
    public ResponseEntity<List<PilotoResponseDTO>> listarPilotos() {
        return ResponseEntity.ok(pilotoService.listarPilotos());
    }

    @Operation(summary = "Buscar piloto por ID", description = "Obtiene los detalles de un piloto específico mediante su identificador único.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Piloto encontrado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Piloto no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<PilotoResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(pilotoService.buscarPorId(id));
    }

    @Operation(
            summary = "Crear nuevo piloto", 
            description = "Registra un nuevo piloto en la base de datos de la DGAC.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos requeridos para registrar un nuevo piloto con su licencia",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    name = "Ejemplo de Registro",
                                    value = "{\n  \"nombre\": \"Juan\",\n  \"apellido\": \"Pérez\",\n  \"numeroLicencia\": \"LIC-DGAC-2026-99A\",\n  \"telefono\": \"+56912345678\",\n  \"activo\": true\n}"
                            )
                    )
            )
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Piloto creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos (ej. licencia duplicada)")
    })
    @PostMapping
    public ResponseEntity<PilotoResponseDTO> crearPiloto(@Valid @RequestBody PilotoRequestDTO dto) {
        PilotoResponseDTO pilotoCreado = pilotoService.crearPiloto(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(pilotoCreado);
    }

    @Operation(
            summary = "Actualizar piloto", 
            description = "Modifica los datos de un piloto existente en el sistema.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Nuevos parámetros del piloto a actualizar",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    name = "Ejemplo de Actualización",
                                    value = "{\n  \"nombre\": \"Juan\",\n  \"apellido\": \"Pérez\",\n  \"numeroLicencia\": \"LIC-DGAC-2026-99A\",\n  \"telefono\": \"+56987654321\",\n  \"activo\": false\n}"
                            )
                    )
            )
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Piloto actualizado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Piloto no encontrado"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
    })
    @PutMapping("/{id}")
    public ResponseEntity<PilotoResponseDTO> actualizarPiloto(
            @PathVariable Long id,
            @Valid @RequestBody PilotoRequestDTO dto) {

        return ResponseEntity.ok(pilotoService.actualizarPiloto(id, dto));
    }

    @Operation(summary = "Eliminar piloto", description = "Elimina un piloto del sistema mediante su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Piloto eliminado exitosamente (Sin contenido)"),
            @ApiResponse(responseCode = "404", description = "Piloto no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPiloto(@PathVariable Long id) {
        pilotoService.eliminarPiloto(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Buscar piloto por licencia", description = "Busca un piloto exacto utilizando su número de licencia aeronáutica.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Piloto encontrado"),
            @ApiResponse(responseCode = "404", description = "Licencia no registrada")
    })
    @GetMapping("/buscar-licencia")
    public ResponseEntity<PilotoResponseDTO> buscarPorLicencia(
            @RequestParam String numeroLicencia) {

        return ResponseEntity.ok(pilotoService.buscarPorLicencia(numeroLicencia));
    }

    @Operation(summary = "Filtrar pilotos por estado", description = "Obtiene una lista de pilotos filtrados según su estado (activos o inactivos).")
    @ApiResponse(responseCode = "200", description = "Búsqueda realizada exitosamente")
    @GetMapping("/estado")
    public ResponseEntity<List<PilotoResponseDTO>> listarPorEstado(
            @RequestParam Boolean activo) {

        return ResponseEntity.ok(pilotoService.listarPorEstado(activo));
    }

    @Operation(summary = "Buscar pilotos por apellido", description = "Obtiene una lista de pilotos que coincidan con el apellido proporcionado.")
    @ApiResponse(responseCode = "200", description = "Búsqueda realizada exitosamente")
    @GetMapping("/buscar-apellido")
    public ResponseEntity<List<PilotoResponseDTO>> buscarPorApellido(
            @RequestParam String apellido) {

        return ResponseEntity.ok(pilotoService.buscarPorApellido(apellido));
    }

    @Operation(summary = "Consultar estado de Usuarios (WebClient)", description = "Endpoint de integración que se comunica con el microservicio de Usuarios para verificar su disponibilidad.")
    @ApiResponse(responseCode = "200", description = "Comunicación exitosa con el microservicio de Usuarios")
    @GetMapping("/usuarios")
    public ResponseEntity<String> consultarUsuarios() {
        return ResponseEntity.ok(pilotoService.consultarMicroservicioUsuarios());
    }
}