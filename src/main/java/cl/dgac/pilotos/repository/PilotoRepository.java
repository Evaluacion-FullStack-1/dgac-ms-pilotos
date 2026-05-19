package cl.dgac.pilotos.repository;

import cl.dgac.pilotos.model.Piloto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface PilotoRepository extends JpaRepository<Piloto, Long> {

    Optional<Piloto> findByNumeroLicencia(String numeroLicencia);

    List<Piloto> findByActivo(Boolean activo);

    @Query("SELECT p FROM Piloto p WHERE LOWER(p.apellido) LIKE LOWER(CONCAT('%', :apellido, '%'))")
    List<Piloto> buscarPorApellido(String apellido);
}