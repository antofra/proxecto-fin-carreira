package org.cuacfm.concursos.modelo.repositorio;

import java.util.Collection;

import org.cuacfm.concursos.modelo.entidad.maestro.Integrante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IntegranteRepository extends JpaRepository<Integrante, Long> {

    @Query("select i from Integrante i where i.programa.id = :idPrograma")
    Collection<Integrante> findByPrograma(@Param("idPrograma") Long idPrograma);

}
