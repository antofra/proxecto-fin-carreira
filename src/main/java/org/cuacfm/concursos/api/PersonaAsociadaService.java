package org.cuacfm.concursos.api;

import java.util.Collection;

import org.cuacfm.concursos.modelo.entidad.maestro.PersonaAsociada;

public interface PersonaAsociadaService {

    void crear(PersonaAsociada asociada);

    void actualizar(Long id, PersonaAsociada asociada);

    void borrar(Long id);

    Collection<PersonaAsociada> listar();

    Collection<PersonaAsociada> listarByNombre(String nombre);

    PersonaAsociada getByNombreApellidosAndDni(String nombre, String apellidos, String dni);
}
