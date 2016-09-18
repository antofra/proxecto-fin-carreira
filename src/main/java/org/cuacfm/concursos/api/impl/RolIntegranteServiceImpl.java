package org.cuacfm.concursos.api.impl;

import java.util.Collection;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.cuacfm.concursos.api.RolIntegranteService;
import org.cuacfm.concursos.modelo.entidad.maestro.RolIntegrante;
import org.cuacfm.concursos.modelo.repositorio.RolIntegranteRepository;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class RolIntegranteServiceImpl implements RolIntegranteService {

    @Inject
    private RolIntegranteRepository rolIntegranteRepository;

    @Override
    public void crear(RolIntegrante rol) {
        this.rolIntegranteRepository.save(rol);
    }

    @Override
    public void actualizar(Long id, RolIntegrante rol) {
        RolIntegrante r = this.rolIntegranteRepository.findOne(id);
        r.setRol(rol.getRol());
        this.rolIntegranteRepository.save(r);
    }

    @Override
    public void borrar(Long id) {
        this.rolIntegranteRepository.delete(id);
    }

    @Override
    public Collection<RolIntegrante> listar() {
        return this.rolIntegranteRepository.findAll();
    }

    @Override
    public RolIntegrante getByRol(String rol) {
        return this.rolIntegranteRepository.findByRol(rol);
    }

}
