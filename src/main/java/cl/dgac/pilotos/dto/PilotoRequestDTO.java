package cl.dgac.pilotos.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(description = "Modelo de petición para la creación o actualización de un piloto")
public class PilotoRequestDTO {

    @Schema(description = "Nombre del piloto", example = "Andrés")
    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @Schema(description = "Apellido del piloto", example = "Salazar")
    @NotBlank(message = "El apellido es obligatorio")
    private String apellido;

    @Schema(description = "RUT del piloto, incluyendo guion y dígito verificador", example = "15123456-7")
    @NotBlank(message = "El RUT es obligatorio")
    private String rut;

    @Schema(description = "Correo electrónico de contacto", example = "andres.salazar@dgac.cl")
    @Email(message = "El email debe tener un formato válido")
    @NotBlank(message = "El email es obligatorio")
    private String email;

    @Schema(description = "Número de teléfono de contacto, idealmente con código de país", example = "+56987654321")
    private String telefono;

    @Schema(description = "Número de licencia aeronáutica oficial otorgada por la DGAC", example = "LIC-2023-098")
    @NotBlank(message = "El número de licencia es obligatorio")
    private String numeroLicencia;

    @Schema(description = "Categoría de la licencia de vuelo (ej. Comercial, Privado, Transporte)", example = "Comercial de Primera Clase")
    @NotBlank(message = "La categoría de licencia es obligatoria")
    private String categoriaLicencia;

    @Schema(description = "Indicador de si el piloto se encuentra activo para operar", example = "true")
    @NotNull(message = "El estado activo es obligatorio")
    private Boolean activo;
}