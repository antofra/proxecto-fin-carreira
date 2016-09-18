package org.cuacfm.concursos.api;

import java.util.Collection;
import java.util.Set;

import org.cuacfm.concursos.modelo.entidad.edicion.EdicionConcurso;
import org.cuacfm.concursos.web.controller.CriteriosConsultaEdicionDto;

public interface EdicionConcursoService {

    void crear(EdicionConcurso edicionConcurso);

    void actualizar(Long idEdicion, EdicionConcurso ec);

    void borrar(Long idEdicion);

    Collection<EdicionConcurso> listar(CriteriosConsultaEdicionDto c);

    EdicionConcurso get(Long idEdicion);

    Set<ResultadoCategoriaEdicionDto> getResultados(Long idEdicion);

}
