package org.cuacfm.concursos.modelo.repositorio;

import java.util.Collection;

import org.cuacfm.concursos.modelo.entidad.edicion.ProgramaEdicion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProgramaEdicionRepository extends JpaRepository<ProgramaEdicion, Long> {

    @Query("select pe from ProgramaEdicion pe where pe.edicion.id = :idEdicion")
    Collection<ProgramaEdicion> findByIdEdicion(@Param("idEdicion") Long idEdicion);

    @Query("select pe from ProgramaEdicion pe where pe.edicion.id = :idEdicion and pe.programa.id = :idPrograma")
    ProgramaEdicion findByIdEdicionAndIdPrograma(@Param("idEdicion") Long idEdicion,
            @Param("idPrograma") Long idPrograma);

}