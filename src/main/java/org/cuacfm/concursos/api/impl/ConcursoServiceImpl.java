package org.cuacfm.concursos.api.impl;

import java.util.Collection;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.cuacfm.concursos.api.ConcursoService;
import org.cuacfm.concursos.modelo.entidad.concurso.Concurso;
import org.cuacfm.concursos.modelo.repositorio.ConcursoRepository;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ConcursoServiceImpl implements ConcursoService {

    @Inject
    private ConcursoRepository concursoRepository;

    @Override
    public void crear(Concurso c) {
        concursoRepository.save(c);
    }

    @Override
    public void actualizar(Long id, Concurso c) {
        Concurso cp = concursoRepository.findOne(id);
        cp.setNombre(c.getNombre());
        concursoRepository.save(cp);
    }

    @Override
    public void borrar(Long id) {
        concursoRepository.delete(id);
    }

    @Override
    public Collection<Concurso> listar() {
        return concursoRepository.findAll();
    }

    @Override
    public Concurso get(Long id) {
        Concurso concurso = this.concursoRepository.findOne(id);
        concurso.getEdiciones();

        return concurso;
    }

}
