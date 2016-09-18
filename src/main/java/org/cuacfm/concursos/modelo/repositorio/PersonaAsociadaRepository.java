package org.cuacfm.concursos.modelo.repositorio;

import java.util.Collection;

import org.cuacfm.concursos.modelo.entidad.maestro.PersonaAsociada;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaAsociadaRepository extends JpaRepository<PersonaAsociada, Long> {

    public Collection<PersonaAsociada> findByNombreIgnoreCaseContaining(String nombre);

    public PersonaAsociada findByNombreAndApellidosAndDni(String nombre, String apellidos, String dni);

}
