package org.cuacfm.concursos.modelo.repositorio;

import java.util.Optional;

import org.cuacfm.concursos.modelo.entidad.seguridad.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByLogin(String login);

}
