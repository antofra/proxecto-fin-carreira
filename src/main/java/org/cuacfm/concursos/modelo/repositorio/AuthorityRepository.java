package org.cuacfm.concursos.modelo.repositorio;

import org.cuacfm.concursos.modelo.entidad.seguridad.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, String> {

    Authority findByName(String nombre);

}
