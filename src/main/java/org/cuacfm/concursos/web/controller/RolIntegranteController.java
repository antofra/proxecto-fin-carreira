package org.cuacfm.concursos.web.controller;

import java.net.URISyntaxException;
import java.util.Collection;

import javax.inject.Inject;

import org.cuacfm.concursos.api.RolIntegranteService;
import org.cuacfm.concursos.modelo.entidad.maestro.RolIntegrante;
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
@RequestMapping("/api/roles")
@Secured("ROLE_ADMIN")
public class RolIntegranteController {

    @Inject
    private RolIntegranteService rolIntegranteService;

    @ApiOperation(nickname = "Anadir rol", value = "Anade un nuevo rol")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<RolIntegrante> create(@RequestBody RolIntegrante rol) throws URISyntaxException {
        rolIntegranteService.crear(rol);
        return new ResponseEntity<RolIntegrante>(rol, HttpStatus.CREATED);
    }

    @ApiOperation(nickname = "Listar roles", value = "Consulta todos los roles")
    @RequestMapping(method = RequestMethod.GET)
    public Collection<RolIntegrante> get() throws URISyntaxException {
        return rolIntegranteService.listar();
    }

    @ApiOperation(nickname = "Actualizar rol", value = "Actualiza un rol")
    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public ResponseEntity<RolIntegrante> update(@PathVariable Long id, @RequestBody RolIntegrante rol) {
        rolIntegranteService.actualizar(id, rol);
        return ResponseEntity.ok(rol);
    }

    @ApiOperation(nickname = "Eliminar rol", value = "Elimina un rol")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        rolIntegranteService.borrar(id);
    }
}
