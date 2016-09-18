package org.cuacfm.concursos.api.impl;

import java.util.Collection;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.cuacfm.concursos.api.IntegranteService;
import org.cuacfm.concursos.modelo.entidad.maestro.Integrante;
import org.cuacfm.concursos.modelo.repositorio.IntegranteRepository;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class IntegranteServiceImpl implements IntegranteService {

    @Inject
    private IntegranteRepository integranteRepository;

    @Override
    public void crear(Long idPrograma, Integrante i) {
        integranteRepository.save(i);
    }

    @Override
    public Collection<Integrante> listarByPrograma(Long idPrograma) {
        return this.integranteRepository.findByPrograma(idPrograma);
    }

    @Override
    public Integrante get(Long id) {
        return this.integranteRepository.getOne(id);
    }

    @Override
    public void actualizar(Long id, Integrante i) {
        Integrante integrante = this.integranteRepository.getOne(id);
        integrante.setRoles(i.getRoles());
        this.integranteRepository.save(integrante);
    }

    @Override
    public void borrar(Long id) {
        this.integranteRepository.delete(id);
    }

}
