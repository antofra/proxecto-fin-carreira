package org.cuacfm.concursos.modelo.repositorio;

import java.util.Collection;

import org.cuacfm.concursos.modelo.entidad.edicion.EdicionConcurso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EdicionConcursoRepository extends JpaRepository<EdicionConcurso, Long> {

    @Query("select ec from EdicionConcurso ec where ec.concurso.id = :idConcurso")
    Collection<EdicionConcurso> findByIdConcurso(@Param("idConcurso") Long idConcurso);

}
