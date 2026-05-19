package cl.dgac.pilotos.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PilotoRequestDTO {

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotBlank(message = "El apellido es obligatorio")
    private String apellido;

    @NotBlank(message = "El RUT es obligatorio")
    private String rut;

    @Email(message = "El email debe tener un formato válido")
    @NotBlank(message = "El email es obligatorio")
    private String email;

    private String telefono;

    @NotBlank(message = "El número de licencia es obligatorio")
    private String numeroLicencia;

    @NotBlank(message = "La categoría de licencia es obligatoria")
    private String categoriaLicencia;

    @NotNull(message = "El estado activo es obligatorio")
    private Boolean activo;
}