package org.cuacfm.concursos.api;

import java.util.Collection;
import java.util.Optional;

import org.cuacfm.concursos.modelo.entidad.seguridad.Usuario;

public interface UsuarioService {

    Optional<Usuario> get(String login);

    void crearUsuario(Usuario usuario);

    Collection<Usuario> listar();

    Usuario getUserWithAuthorities(String login);
}
