package org.cuacfm.concursos.api.impl;

import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.cuacfm.concursos.api.UsuarioService;
import org.cuacfm.concursos.aplicacion.seguridad.AuthoritiesConstants;
import org.cuacfm.concursos.aplicacion.seguridad.util.RandomUtil;
import org.cuacfm.concursos.modelo.entidad.seguridad.Authority;
import org.cuacfm.concursos.modelo.entidad.seguridad.Usuario;
import org.cuacfm.concursos.modelo.repositorio.AuthorityRepository;
import org.cuacfm.concursos.modelo.repositorio.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UsuarioServiceImpl implements UsuarioService {

    @Inject
    private UsuarioRepository usuarioRepository;

    @Inject
    private AuthorityRepository authorityRepository;

    @Inject
    private PasswordEncoder passwordEncoder;

    @Override
    public Optional<Usuario> get(String login) {
        return this.usuarioRepository.findByLogin(login);
    }

    @Override
    public void crearUsuario(Usuario usuario) {

        String password = StringUtils.reverse(usuario.getLogin());
        String encryptedPassword = passwordEncoder.encode(password);
        usuario.setPassword(encryptedPassword);
        usuario.setResetKey(RandomUtil.generateResetKey());
        usuario.setResetDate(ZonedDateTime.now());
        usuario.setActivated(true);
        Set<Authority> authorities = new HashSet<>();
        authorities.add(this.authorityRepository.findByName(AuthoritiesConstants.USER));

        this.usuarioRepository.save(usuario);
    }

    @Override
    public Collection<Usuario> listar() {
        return this.usuarioRepository.findAll();
    }

    @Override
    public Usuario getUserWithAuthorities(String login) {
        Usuario user = usuarioRepository.findByLogin(login).get();
        user.getAuthorities().size(); // eagerly load the association
        return user;
    }

}
