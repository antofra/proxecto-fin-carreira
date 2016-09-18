package org.cuacfm.concursos.api;

import java.util.Collection;

import org.cuacfm.concursos.modelo.entidad.maestro.NoAsociada;

public interface NoAsociadaService {

    void crear(NoAsociada noAsociada);

    void actualizar(Long id, NoAsociada noAsociada);

    void borrar(Long id);

    Collection<NoAsociada> listar();
}
