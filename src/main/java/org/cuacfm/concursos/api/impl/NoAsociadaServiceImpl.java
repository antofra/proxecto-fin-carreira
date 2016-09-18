package org.cuacfm.concursos.api.impl;

import java.util.Collection;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.cuacfm.concursos.api.NoAsociadaService;
import org.cuacfm.concursos.modelo.entidad.maestro.NoAsociada;
import org.cuacfm.concursos.modelo.repositorio.NoAsociadaRepository;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class NoAsociadaServiceImpl implements NoAsociadaService {

    @Inject
    private NoAsociadaRepository noAsociadaRepository;

    @Override
    public void crear(NoAsociada noAsociada) {
        noAsociadaRepository.save(noAsociada);
    }

    @Override
    public Collection<NoAsociada> listar() {
        return noAsociadaRepository.findAll();
    }

    @Override
    public void actualizar(Long id, NoAsociada noAsociada) {
        NoAsociada p = noAsociadaRepository.findOne(id);
        p.setNombre(noAsociada.getNombre());

        noAsociadaRepository.save(p);
    }

    @Override
    public void borrar(Long id) {
        noAsociadaRepository.delete(id);
    }

}
