package org.cuacfm.concursos.web.controller;

import java.net.URISyntaxException;
import java.util.Set;

import javax.inject.Inject;

import org.cuacfm.concursos.api.EdicionConcursoService;
import org.cuacfm.concursos.api.ResultadoCategoriaEdicionDto;
import org.cuacfm.concursos.api.VotoEdicionDto;
import org.cuacfm.concursos.api.VotoService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/votos")
public class VotoController {

    @Inject
    private EdicionConcursoService edicionConcursoService;

    @Inject
    private VotoService votoService;

    @ApiOperation(nickname = "Obtener resultado", value = "Lista todas las categorias de una edicion")
    @RequestMapping(method = RequestMethod.GET)
    public Set<ResultadoCategoriaEdicionDto> listar(@PathVariable Long idEdicion) throws URISyntaxException {
        return this.edicionConcursoService.getResultados(idEdicion);
    }

    @ApiOperation(nickname = "Realizar votacion", value = "Emite los votos de un usuario")
    @RequestMapping(method = RequestMethod.POST)
    @Secured({ "ROLE_USER" })
    public void votar(@RequestBody VotoEdicionDto votoEdicion) throws URISyntaxException {
        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        this.votoService.emitirVoto(user.getUsername(), votoEdicion);
    }

}
