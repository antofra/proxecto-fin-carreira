package org.cuacfm.concursos.api;

import java.util.Collection;

import org.cuacfm.concursos.modelo.entidad.concurso.Concurso;

public interface ConcursoService {

    void crear(Concurso nombre);

    void actualizar(Long id, Concurso c);

    void borrar(Long id);

    Collection<Concurso> listar();

    Concurso get(Long id);
}
