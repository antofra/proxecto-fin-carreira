package org.cuacfm.concursos.modelo.repositorio;

import org.cuacfm.concursos.modelo.entidad.maestro.RolIntegrante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolIntegranteRepository extends JpaRepository<RolIntegrante, Long> {

    RolIntegrante findByRol(String rol);

}