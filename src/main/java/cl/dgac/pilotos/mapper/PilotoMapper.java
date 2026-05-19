package cl.dgac.pilotos.mapper;

import cl.dgac.pilotos.dto.PilotoRequestDTO;
import cl.dgac.pilotos.dto.PilotoResponseDTO;
import cl.dgac.pilotos.model.Piloto;
import org.springframework.stereotype.Component;

@Component
public class PilotoMapper {

    public Piloto toEntity(PilotoRequestDTO dto) {
        Piloto piloto = new Piloto();

        piloto.setNombre(dto.getNombre());
        piloto.setApellido(dto.getApellido());
        piloto.setRut(dto.getRut());
        piloto.setEmail(dto.getEmail());
        piloto.setTelefono(dto.getTelefono());
        piloto.setNumeroLicencia(dto.getNumeroLicencia());
        piloto.setCategoriaLicencia(dto.getCategoriaLicencia());
        piloto.setActivo(dto.getActivo());

        return piloto;
    }

    public PilotoResponseDTO toDTO(Piloto piloto) {
        PilotoResponseDTO dto = new PilotoResponseDTO();

        dto.setId(piloto.getId());
        dto.setNombre(piloto.getNombre());
        dto.setApellido(piloto.getApellido());
        dto.setRut(piloto.getRut());
        dto.setEmail(piloto.getEmail());
        dto.setTelefono(piloto.getTelefono());
        dto.setNumeroLicencia(piloto.getNumeroLicencia());
        dto.setCategoriaLicencia(piloto.getCategoriaLicencia());
        dto.setActivo(piloto.getActivo());

        return dto;
    }

    public void updateEntity(Piloto piloto, PilotoRequestDTO dto) {
        piloto.setNombre(dto.getNombre());
        piloto.setApellido(dto.getApellido());
        piloto.setRut(dto.getRut());
        piloto.setEmail(dto.getEmail());
        piloto.setTelefono(dto.getTelefono());
        piloto.setNumeroLicencia(dto.getNumeroLicencia());
        piloto.setCategoriaLicencia(dto.getCategoriaLicencia());
        piloto.setActivo(dto.getActivo());
    }
}