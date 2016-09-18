package org.cuacfm.concursos.api;

import java.util.Collection;

import org.cuacfm.concursos.modelo.entidad.maestro.RolIntegrante;

public interface RolIntegranteService {

    void crear(RolIntegrante rol);

    RolIntegrante getByRol(String rol);

    void actualizar(Long id, RolIntegrante rol);

    void borrar(Long id);

    Collection<RolIntegrante> listar();
}
