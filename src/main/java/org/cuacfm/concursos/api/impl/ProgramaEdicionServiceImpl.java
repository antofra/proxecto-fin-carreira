package org.cuacfm.concursos.api.impl;

import java.util.Collection;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.cuacfm.concursos.api.ProgramaEdicionService;
import org.cuacfm.concursos.modelo.entidad.edicion.ProgramaEdicion;
import org.cuacfm.concursos.modelo.repositorio.ProgramaEdicionRepository;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ProgramaEdicionServiceImpl implements ProgramaEdicionService {

    @Inject
    private ProgramaEdicionRepository programaEdicionRepository;

    @Override
    public void crear(ProgramaEdicion pe) {
        this.programaEdicionRepository.save(pe);
    }

    @Override
    public Collection<ProgramaEdicion> listar(Long idEdicion) {
        return this.programaEdicionRepository.findByIdEdicion(idEdicion);
    }

    @Override
    public ProgramaEdicion get(Long idProgramaEdicion) {
        return programaEdicionRepository.findOne(idProgramaEdicion);
    }

    @Override
    public void actualizar(Long idProgramaEdicion, ProgramaEdicion pe) {
        ProgramaEdicion programaEdicion = this.programaEdicionRepository.findOne(idProgramaEdicion);
        pe.getIntegrantes().clear();
        pe.getIntegrantes().addAll(pe.getIntegrantes());

        this.programaEdicionRepository.save(programaEdicion);
    }

    @Override
    public void borrar(Long idProgramaEdicion) {
        this.programaEdicionRepository.delete(idProgramaEdicion);
    }

}
