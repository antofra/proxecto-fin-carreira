package org.cuacfm.concursos.modelo.repositorio;

import org.cuacfm.concursos.modelo.entidad.maestro.Programa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProgramaRepository extends JpaRepository<Programa, Long> {

}
