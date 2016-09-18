package org.cuacfm.concursos.api;

import java.util.Collection;

import org.cuacfm.concursos.modelo.entidad.edicion.ParticipanteEdicion;

public interface ParticipanteEdicionService {

    void crear(ParticipanteEdicion participanteEdicion);

    Collection<ParticipanteEdicion> listar(Long idEdicion);

    ParticipanteEdicion get(Long idParticipanteEdicion);

    void actualizar(Long idParticipanteEdicion, ParticipanteEdicion pe);

    void borrar(Long idParticipanteEdicion);

}
