package org.cuacfm.concursos.api.impl;

import java.util.Collection;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.cuacfm.concursos.api.CategoriaService;
import org.cuacfm.concursos.modelo.entidad.concurso.Categoria;
import org.cuacfm.concursos.modelo.repositorio.CategoriaRepository;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CategoriaServiceImpl implements CategoriaService {

    @Inject
    private CategoriaRepository categoriaRepository;

    @Override
    public void crear(Categoria c) {
        categoriaRepository.save(c);
    }

    @Override
    public void actualizar(Long idCategoria, Categoria c) {
        Categoria categoria = this.categoriaRepository.findOne(idCategoria);
        categoria.setNombre(c.getNombre());

        this.categoriaRepository.save(categoria);
    }

    @Override
    public void borrar(Long idCategoria) {
        this.categoriaRepository.delete(idCategoria);
    }

    @Override
    public Collection<Categoria> listar(String nombre) {
        if (StringUtils.isBlank(nombre)) {
            return categoriaRepository.findAll();
        }

        return categoriaRepository.findByNombreContainingIgnoreCase(nombre);
    }

    @Override
    public Categoria get(Long id) {
        return this.categoriaRepository.findOne(id);
    }

}
