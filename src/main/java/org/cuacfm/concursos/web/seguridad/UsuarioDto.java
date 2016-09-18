package org.cuacfm.concursos.web.seguridad;

import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.cuacfm.concursos.aplicacion.config.Constants;
import org.cuacfm.concursos.modelo.entidad.seguridad.Authority;
import org.cuacfm.concursos.modelo.entidad.seguridad.Usuario;

public class UsuarioDto {

    @NotNull
    @Pattern(regexp = Constants.LOGIN_REGEX)
    @Size(min = 1, max = 50)
    private String login;

    private boolean activated = false;

    private Set<String> authorities;

    public UsuarioDto() {
    }

    public UsuarioDto(Usuario user) {
        this(user.getLogin(), user.getActivated(),
                user.getAuthorities().stream().map(Authority::getName).collect(Collectors.toSet()));
    }

    public UsuarioDto(String login, boolean activated, Set<String> authorities) {

        this.login = login;
        this.activated = activated;
        this.authorities = authorities;
    }

    public String getLogin() {
        return login;
    }

    public boolean isActivated() {
        return activated;
    }

    public Set<String> getAuthorities() {
        return authorities;
    }

    @Override
    public String toString() {
        return "UserDTO{" + "login='" + login + '\'' + ", activated=" + activated + ", authorities=" + authorities
                + "}";
    }
}
