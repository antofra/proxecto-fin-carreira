package org.cuacfm.concursos.api;

import java.util.Collection;

import org.cuacfm.concursos.modelo.entidad.concurso.Categoria;

public interface CategoriaService {

    void crear(Categoria c);

    void actualizar(Long idCategoria, Categoria c);

    void borrar(Long idCategoria);

    Collection<Categoria> listar(String nombre);

    Categoria get(Long id);
}
