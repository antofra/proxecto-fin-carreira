package org.cuacfm.concursos.web.controller;

import java.util.Collection;

import javax.inject.Inject;

import org.cuacfm.concursos.api.IntegranteService;
import org.cuacfm.concursos.modelo.entidad.maestro.Integrante;
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
@RequestMapping("/api/programas/{idPrograma}/integrantes")
@Secured("ROLE_ADMIN")
public class IntegranteController {

    @Inject
    private IntegranteService integranteService;

    @ApiOperation(nickname = "Listar integrantes", value = "Lista todos los integrantes de un programa")
    @RequestMapping(method = RequestMethod.GET)
    public Collection<Integrante> getAll(@PathVariable Long idPrograma) {
        return integranteService.listarByPrograma(idPrograma);
    }

    @ApiOperation(nickname = "Obtener integrante", value = "Obtiene un integrante")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Integrante get(@PathVariable Long id) {
        return integranteService.get(id);
    }

    @ApiOperation(nickname = "Crear integrante", value = "Crea un nuevo integrante")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Integrante> create(@PathVariable Long idPrograma, @RequestBody Integrante integrante) {
        integranteService.crear(idPrograma, integrante);
        return new ResponseEntity<Integrante>(integrante, HttpStatus.CREATED);
    }

    @ApiOperation(nickname = "Actualizar integrante", value = "Actualiza un integrante")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Integrante> update(@PathVariable Long id, @RequestBody Integrante i) {
        integranteService.actualizar(id, i);
        return ResponseEntity.ok(i);
    }

    @ApiOperation(nickname = "Borrar integrante", value = "Borra un integrante")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        integranteService.borrar(id);
    }

}
