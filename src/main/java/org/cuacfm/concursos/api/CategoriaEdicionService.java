package org.cuacfm.concursos.api;

import java.util.Collection;

import org.cuacfm.concursos.modelo.entidad.concurso.Categoria;
import org.cuacfm.concursos.modelo.entidad.edicion.CategoriaEdicion;
import org.cuacfm.concursos.modelo.entidad.edicion.ParticipanteEdicion;

public interface CategoriaEdicionService {

    CategoriaEdicion crear(Long idEdicion, Categoria categoria);

    CategoriaEdicion get(Long idCategoriaEdicion);

    Collection<CategoriaEdicion> listar(Long idEdicion);

    void anadirParticipantes(Long idCategoriaEdicion, Collection<ParticipanteEdicion> participantes);

    void actualizar(Long idCategoriaEdicion, CategoriaEdicion ce);

    void borrar(Long idCategoriaEdicion);

}
