package org.cuacfm.concursos.api.impl;

import java.util.Collection;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.cuacfm.concursos.api.ProgramaService;
import org.cuacfm.concursos.api.UsuarioService;
import org.cuacfm.concursos.modelo.entidad.maestro.Programa;
import org.cuacfm.concursos.modelo.entidad.seguridad.Usuario;
import org.cuacfm.concursos.modelo.repositorio.ProgramaRepository;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ProgramaServiceImpl implements ProgramaService {

    @Inject
    private ProgramaRepository programaRepository;

    @Inject
    private UsuarioService usuarioService;

    @Override
    public void crear(Programa p) {
        Usuario u = new Usuario();
        u.setLogin(p.getNombre());

        this.usuarioService.crearUsuario(u);
        p.setUsuario(u);
        programaRepository.save(p);
    }

    @Override
    public void actualizar(Long id, Programa p) {
        Programa programa = this.programaRepository.findOne(id);
        programa.setNombre(p.getNombre());
        programa.setDireccionCorreo(p.getDireccionCorreo());

        this.programaRepository.save(programa);
    }

    @Override
    public void borrar(Long id) {
        this.programaRepository.delete(id);
    }

    @Override
    public Collection<Programa> listar() {
        return programaRepository.findAll();
    }

    @Override
    public Programa get(Long id) {
        Programa p = this.programaRepository.findOne(id);
        p.getIntegrantes();

        return p;
    }

}
