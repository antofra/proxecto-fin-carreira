package org.cuacfm.concursos.modelo.repositorio;

import org.cuacfm.concursos.modelo.entidad.concurso.Concurso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConcursoRepository extends JpaRepository<Concurso, Long> {
}
