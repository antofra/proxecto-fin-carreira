package org.cuacfm.concursos.api.impl;

import java.util.Collection;

import javax.inject.Inject;

import org.cuacfm.concursos.api.PersonaAsociadaService;
import org.cuacfm.concursos.modelo.entidad.maestro.PersonaAsociada;
import org.cuacfm.concursos.modelo.repositorio.PersonaAsociadaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class PersonaAsociadaServiceImpl implements PersonaAsociadaService {

    @Inject
    private PersonaAsociadaRepository personaAsociadaRepository;

    @Override
    public void crear(PersonaAsociada asociada) {
        personaAsociadaRepository.save(asociada);
    }

    @Override
    public void actualizar(Long id, PersonaAsociada asociada) {
        PersonaAsociada p = personaAsociadaRepository.findOne(id);
        p.setNombre(asociada.getNombre());

        personaAsociadaRepository.save(p);
    }

    @Override
    public void borrar(Long id) {
        personaAsociadaRepository.delete(id);
    }

    @Override
    public Collection<PersonaAsociada> listar() {
        return personaAsociadaRepository.findAll();
    }

    @Override
    public Collection<PersonaAsociada> listarByNombre(String nombre) {
        return this.personaAsociadaRepository.findByNombreIgnoreCaseContaining(nombre);
    }

    @Override
    public PersonaAsociada getByNombreApellidosAndDni(String nombre, String apellidos, String dni) {
        return this.personaAsociadaRepository.findByNombreAndApellidosAndDni(nombre, apellidos, dni);
    }

}
