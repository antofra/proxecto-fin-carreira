package org.cuacfm.concursos.web.seguridad;

import java.util.Optional;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.cuacfm.concursos.api.UsuarioService;
import org.cuacfm.concursos.aplicacion.seguridad.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AccountResource {

    private final Logger log = LoggerFactory.getLogger(AccountResource.class);

    @Inject
    private UsuarioService usuarioService;

    /**
     * GET /authenticate : check if the user is authenticated, and return its
     * login.
     *
     * @param request
     *            the HTTP request
     * @return the login if the user is authenticated
     */
    @RequestMapping(value = "/authenticate", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String isAuthenticated(HttpServletRequest request) {
        log.debug("REST request to check if the current user is authenticated");
        return request.getRemoteUser();
    }

    /**
     * GET /account : get the current user.
     *
     * @return the ResponseEntity with status 200 (OK) and the current user in
     *         body, or status 500 (Internal Server Error) if the user couldn't
     *         be returned
     */
    @RequestMapping(value = "/account", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UsuarioDto> getAccount() {
        this.log.error("Login que vamos a comprobar: " + SecurityUtils.getCurrentUserLogin());
        return Optional.ofNullable(usuarioService.getUserWithAuthorities(SecurityUtils.getCurrentUserLogin()))
                .map(user -> new ResponseEntity<>(new UsuarioDto(user), HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
    }

}
