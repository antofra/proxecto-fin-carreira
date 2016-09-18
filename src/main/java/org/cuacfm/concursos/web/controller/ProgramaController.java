package org.cuacfm.concursos.web.controller;

import java.net.URISyntaxException;
import java.util.Collection;

import javax.inject.Inject;

import org.cuacfm.concursos.api.ProgramaService;
import org.cuacfm.concursos.modelo.entidad.maestro.Programa;
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
@RequestMapping("/api/programas")
@Secured("ROLE_ADMIN")
public class ProgramaController {

    @Inject
    private ProgramaService programaService;

    @ApiOperation(nickname = "Listar programas", value = "Lista todos los programas")
    @RequestMapping(method = RequestMethod.GET)
    public Collection<Programa> getAll() {
        return programaService.listar();
    }

    @ApiOperation(nickname = "Obtener programa", value = "Obtiene un programa")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Programa get(@PathVariable Long id) {
        return programaService.get(id);
    }

    @ApiOperation(nickname = "Crear programa", value = "Crea un nuevo programa")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Programa> create(@RequestBody Programa programa) throws URISyntaxException {
        programaService.crear(programa);
        return new ResponseEntity<Programa>(programa, HttpStatus.CREATED);
    }

    @ApiOperation(nickname = "Actualizar programa", value = "Actualiza un programa")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Programa> update(@PathVariable Long id, @RequestBody Programa p) throws URISyntaxException {
        programaService.actualizar(id, p);
        return ResponseEntity.ok(p);
    }

    @ApiOperation(nickname = "Borrar programa", value = "Borra un programa")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        programaService.borrar(id);
    }

}
