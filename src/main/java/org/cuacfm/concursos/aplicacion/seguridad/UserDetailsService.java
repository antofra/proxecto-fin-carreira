package org.cuacfm.concursos.aplicacion.seguridad;

import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.cuacfm.concursos.api.UsuarioService;
import org.cuacfm.concursos.modelo.entidad.seguridad.Usuario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Authenticate a user from the database.
 */
@Component("userDetailsService")
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final Logger log = LoggerFactory.getLogger(UserDetailsService.class);

    @Inject
    private UsuarioService usuarioService;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String login) {
        log.debug("Authenticating {}", login);
        String lowercaseLogin = login.toLowerCase(Locale.ENGLISH);
        Optional<Usuario> usuarioDB = usuarioService.get(lowercaseLogin);
        return usuarioDB.map(usuario -> {
            if (!usuario.getActivated()) {
                throw new UserNotActivatedException("Usuario " + lowercaseLogin + " no esta activado.");
            }
            List<GrantedAuthority> grantedAuthorities = usuario.getAuthorities().stream()
                    .map(authority -> new SimpleGrantedAuthority(authority.getName())).collect(Collectors.toList());
            return new org.springframework.security.core.userdetails.User(lowercaseLogin, usuario.getPassword(),
                    grantedAuthorities);
        }).orElseThrow(
                () -> new UsernameNotFoundException("User " + lowercaseLogin + " was not found in the " + "database"));
    }
}
