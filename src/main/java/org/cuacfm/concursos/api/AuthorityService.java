package org.cuacfm.concursos.api;

import org.cuacfm.concursos.modelo.entidad.seguridad.Authority;

public interface AuthorityService {

    void crear(String nombre);

    Authority get(String nombre);

}
