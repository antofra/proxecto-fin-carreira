package org.cuacfm.concursos.api;

import java.util.Collection;

import org.cuacfm.concursos.modelo.entidad.edicion.ProgramaEdicion;

public interface ProgramaEdicionService {

    void crear(ProgramaEdicion programaEdicion);

    Collection<ProgramaEdicion> listar(Long idEdicion);

    ProgramaEdicion get(Long idProgramaEdicion);

    void actualizar(Long idProgramaEdicion, ProgramaEdicion pe);

    void borrar(Long idProgramaEdicion);

}
