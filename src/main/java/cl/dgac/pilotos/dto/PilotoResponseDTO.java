package cl.dgac.pilotos.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Modelo de respuesta con la información detallada del piloto")
public class PilotoResponseDTO {

    @Schema(description = "Identificador único del piloto en la base de datos", example = "1")
    private Long id;

    @Schema(description = "Nombre del piloto", example = "Andrés")
    private String nombre;

    @Schema(description = "Apellido del piloto", example = "Salazar")
    private String apellido;

    @Schema(description = "RUT del piloto", example = "15123456-7")
    private String rut;

    @Schema(description = "Correo electrónico de contacto", example = "andres.salazar@dgac.cl")
    private String email;

    @Schema(description = "Número de teléfono de contacto", example = "+56987654321")
    private String telefono;

    @Schema(description = "Número de licencia aeronáutica oficial otorgada por la DGAC", example = "LIC-2023-098")
    private String numeroLicencia;

    @Schema(description = "Categoría de la licencia de vuelo", example = "Comercial de Primera Clase")
    private String categoriaLicencia;

    @Schema(description = "Indicador de si el piloto se encuentra activo para operar", example = "true")
    private Boolean activo;
} 