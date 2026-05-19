package cl.dgac.pilotos.dto;

import lombok.Data;

@Data
public class PilotoResponseDTO {

    private Long id;
    private String nombre;
    private String apellido;
    private String rut;
    private String email;
    private String telefono;
    private String numeroLicencia;
    private String categoriaLicencia;
    private Boolean activo;
}