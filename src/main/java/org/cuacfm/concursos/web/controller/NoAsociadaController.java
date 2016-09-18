package org.cuacfm.concursos.web.controller;

import java.net.URISyntaxException;
import java.util.Collection;

import javax.inject.Inject;

import org.cuacfm.concursos.api.NoAsociadaService;
import org.cuacfm.concursos.modelo.entidad.maestro.NoAsociada;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/noasociadas")
@Secured("ROLE_ADMIN")
public class NoAsociadaController {

    @Inject
    private NoAsociadaService noAsociadaService;

    @ApiOperation(nickname = "Listar no asociadas", value = "Lista todas las personas/entidades no asociadas")
    @RequestMapping(method = RequestMethod.GET)
    public Collection<NoAsociada> getAll() {
        return noAsociadaService.listar();
    }

    @ApiOperation(nickname = "Crear no asociada", value = "Crea una nueva persona/entidad no asociada")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<NoAsociada> create(@RequestBody NoAsociada noAsociada) throws URISyntaxException {
        noAsociadaService.crear(noAsociada);
        return new ResponseEntity<NoAsociada>(noAsociada, HttpStatus.CREATED);
    }

    @ApiOperation(nickname = "Actualizar no asociada", value = "Actualiza una persona/entidad no asociada")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<NoAsociada> update(@PathVariable Long id, @RequestBody NoAsociada noasociada)
            throws URISyntaxException {
        noAsociadaService.actualizar(id, noasociada);
        return ResponseEntity.ok(noasociada);
    }

    @ApiOperation(nickname = "Borrar no asociada", value = "Borra una persona/entidad no asociada")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        noAsociadaService.borrar(id);
    }

}
