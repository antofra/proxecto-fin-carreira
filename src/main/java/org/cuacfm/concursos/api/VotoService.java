package org.cuacfm.concursos.api;

import java.util.Collection;

import org.cuacfm.concursos.modelo.entidad.edicion.Voto;

public interface VotoService {

    Collection<Voto> findByCategoriaEdicion(Long id);

    void emitirVoto(String login, VotoEdicionDto votoEdicion);

}
