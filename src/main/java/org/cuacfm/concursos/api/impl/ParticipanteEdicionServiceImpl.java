package org.cuacfm.concursos.api.impl;

import java.util.Collection;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.cuacfm.concursos.api.ParticipanteEdicionService;
import org.cuacfm.concursos.modelo.entidad.edicion.ParticipanteEdicion;
import org.cuacfm.concursos.modelo.repositorio.ParticipanteEdicionRepository;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ParticipanteEdicionServiceImpl implements ParticipanteEdicionService {

    @Inject
    private ParticipanteEdicionRepository participanteEdicionRepository;

    @Override
    public void crear(ParticipanteEdicion participanteEdicion) {
        this.participanteEdicionRepository.save(participanteEdicion);
    }

    @Override
    public Collection<ParticipanteEdicion> listar(Long idEdicion) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ParticipanteEdicion get(Long idParticipanteEdicion) {
        return this.participanteEdicionRepository.findOne(idParticipanteEdicion);
    }

    @Override
    public void actualizar(Long idParticipanteEdicion, ParticipanteEdicion participanteEdicion) {
        // TODO
        ParticipanteEdicion pe = this.participanteEdicionRepository.findOne(idParticipanteEdicion);
    }

    @Override
    public void borrar(Long idParticipanteEdicion) {
        this.participanteEdicionRepository.delete(idParticipanteEdicion);
    }

}
