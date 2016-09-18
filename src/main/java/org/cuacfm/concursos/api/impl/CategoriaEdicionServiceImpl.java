package org.cuacfm.concursos.api.impl;

import java.util.Collection;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.cuacfm.concursos.api.CategoriaEdicionService;
import org.cuacfm.concursos.modelo.entidad.concurso.Categoria;
import org.cuacfm.concursos.modelo.entidad.edicion.CategoriaEdicion;
import org.cuacfm.concursos.modelo.entidad.edicion.EdicionConcurso;
import org.cuacfm.concursos.modelo.entidad.edicion.ParticipanteEdicion;
import org.cuacfm.concursos.modelo.repositorio.CategoriaEdicionRepository;
import org.cuacfm.concursos.modelo.repositorio.EdicionConcursoRepository;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CategoriaEdicionServiceImpl implements CategoriaEdicionService {

    @Inject
    private EdicionConcursoRepository edicionConcursoRepository;

    @Inject
    private CategoriaEdicionRepository categoriaEdicionRepository;

    @Override
    public CategoriaEdicion crear(Long idEdicion, Categoria categoria) {
        EdicionConcurso ec = this.edicionConcursoRepository.findOne(idEdicion);
        CategoriaEdicion ce = new CategoriaEdicion();
        ce.setCategoria(categoria);
        ce.setEdicion(ec);

        ec.getCategoriasEdicion().add(ce);
        this.categoriaEdicionRepository.save(ce);

        return ce;
    }

    @Override
    public void actualizar(Long idCategoriaEdicion, CategoriaEdicion ce) {
        CategoriaEdicion categoriaEdicion = this.categoriaEdicionRepository.findOne(idCategoriaEdicion);
        categoriaEdicion.getParticipantes().clear();
        categoriaEdicion.getParticipantes().addAll(ce.getParticipantes());

        this.categoriaEdicionRepository.save(categoriaEdicion);
    }

    @Override
    public void borrar(Long idCategoriaEdicion) {
        this.categoriaEdicionRepository.delete(idCategoriaEdicion);
    }

    @Override
    public Collection<CategoriaEdicion> listar(Long idEdicion) {
        EdicionConcurso ec = this.edicionConcursoRepository.findOne(idEdicion);
        return ec.getCategoriasEdicion();
    }

    @Override
    public CategoriaEdicion get(Long idCategoriaEdicion) {
        return this.categoriaEdicionRepository.findOne(idCategoriaEdicion);
    }

    @Override
    public void anadirParticipantes(Long idCategoriaEdicion, Collection<ParticipanteEdicion> participantes) {
        CategoriaEdicion ce = this.categoriaEdicionRepository.findOne(idCategoriaEdicion);
        if (ce != null) {
            ce.getParticipantes().addAll(participantes);
        }
    }

}
