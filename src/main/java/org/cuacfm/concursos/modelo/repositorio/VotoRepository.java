package org.cuacfm.concursos.modelo.repositorio;

import java.util.Collection;

import org.cuacfm.concursos.modelo.entidad.edicion.Voto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface VotoRepository extends JpaRepository<Voto, Long> {

    @Query("select v from Voto v where v.categoriaEdicion.id = :id")
    Collection<Voto> findByCategoriaEdicion(@Param("id") Long id);

}
