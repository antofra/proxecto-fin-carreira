package org.cuacfm.concursos.api;

import java.util.Collection;

import org.cuacfm.concursos.modelo.entidad.maestro.Integrante;

public interface IntegranteService {

    void crear(Long idPrograma, Integrante i);

    Collection<Integrante> listarByPrograma(Long idPrograma);

    Integrante get(Long id);

    void actualizar(Long id, Integrante i);

    void borrar(Long id);

}
