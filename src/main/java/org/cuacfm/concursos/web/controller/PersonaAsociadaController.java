package org.cuacfm.concursos.web.controller;

import java.net.URISyntaxException;
import java.util.Collection;

import javax.inject.Inject;

import org.cuacfm.concursos.api.PersonaAsociadaService;
import org.cuacfm.concursos.modelo.entidad.maestro.PersonaAsociada;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/asociadas")
@Secured("ROLE_ADMIN")
public class PersonaAsociadaController {

    @Inject
    private PersonaAsociadaService personaAsociadaService;

    @ApiOperation(nickname = "Listar asociadas", value = "Lista todas las personas asociadas")
    @RequestMapping(method = RequestMethod.GET)
    public Collection<PersonaAsociada> getAll(@RequestParam(required = false, name = "nombre") String nombre) {

        if (nombre == null) {
            return personaAsociadaService.listar();
        } else {
            return personaAsociadaService.listarByNombre(nombre);
        }
    }

    @ApiOperation(nickname = "Crear asociada", value = "Crea una nueva persona asociada")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<PersonaAsociada> create(@RequestBody PersonaAsociada asociada) throws URISyntaxException {
        personaAsociadaService.crear(asociada);
        return new ResponseEntity<PersonaAsociada>(asociada, HttpStatus.CREATED);
    }

    @ApiOperation(nickname = "Actualizar asociada", value = "Actualiza una persona asociada")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<PersonaAsociada> update(@PathVariable Long id, @RequestBody PersonaAsociada asociada)
            throws URISyntaxException {
        personaAsociadaService.actualizar(id, asociada);
        return ResponseEntity.ok(asociada);
    }

    @ApiOperation(nickname = "Borrar asociada", value = "Borra una persona asociada")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        personaAsociadaService.borrar(id);
    }

}
