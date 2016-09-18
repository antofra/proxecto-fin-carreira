package org.cuacfm.concursos.api;

import java.util.Collection;

import org.cuacfm.concursos.modelo.entidad.maestro.Programa;

public interface ProgramaService {

    void crear(Programa p);

    Collection<Programa> listar();

    Programa get(Long id);

    void actualizar(Long id, Programa p);

    void borrar(Long id);

}
