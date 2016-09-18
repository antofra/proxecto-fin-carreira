package org.cuacfm.concursos.api.impl;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.cuacfm.concursos.api.AuthorityService;
import org.cuacfm.concursos.modelo.entidad.seguridad.Authority;
import org.cuacfm.concursos.modelo.repositorio.AuthorityRepository;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class AuthorityServiceImpl implements AuthorityService {

    @Inject
    private AuthorityRepository authorityRepository;

    @Override
    public void crear(String nombre) {
        Authority a = new Authority();
        a.setName(nombre);
        this.authorityRepository.save(a);
    }

    @Override
    public Authority get(String nombre) {
        return this.authorityRepository.findByName(nombre);
    }

}
