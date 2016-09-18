package org.cuacfm.concursos.web.seguridad;

import java.util.Collections;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.cuacfm.concursos.api.UsuarioService;
import org.cuacfm.concursos.aplicacion.seguridad.jwt.JWTConfigurer;
import org.cuacfm.concursos.aplicacion.seguridad.jwt.TokenProvider;
import org.cuacfm.concursos.modelo.entidad.seguridad.Usuario;
import org.cuacfm.concursos.web.seguridad.dto.LoginDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UsuarioController {

    @Inject
    private TokenProvider tokenProvider;

    @Inject
    private AuthenticationManager authenticationManager;

    @Inject
    private UsuarioService usuarioService;

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> authorize(@Valid @RequestBody LoginDTO loginDTO, HttpServletResponse response) {

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                loginDTO.getUsername(), loginDTO.getPassword());

        try {
            Authentication authentication = this.authenticationManager.authenticate(authenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            boolean rememberMe = (loginDTO.isRememberMe() == null) ? false : loginDTO.isRememberMe();
            String jwt = tokenProvider.createToken(authentication, rememberMe);
            response.addHeader(JWTConfigurer.AUTHORIZATION_HEADER, "Bearer " + jwt);
            return ResponseEntity.ok(new JWTToken(jwt));
        } catch (AuthenticationException exception) {
            return new ResponseEntity<>(
                    Collections.singletonMap("AuthenticationException", exception.getLocalizedMessage()),
                    HttpStatus.UNAUTHORIZED);
        }
    }

    @RequestMapping(value = "/{login}", method = RequestMethod.GET)
    public Usuario get(@PathVariable String login) {
        return usuarioService.get(login).get();
    }
}
