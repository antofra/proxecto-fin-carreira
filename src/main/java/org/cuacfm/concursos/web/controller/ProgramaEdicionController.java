package org.cuacfm.concursos.web.controller;

import java.util.Collection;

import javax.inject.Inject;

import org.cuacfm.concursos.api.ProgramaEdicionService;
import org.cuacfm.concursos.modelo.entidad.edicion.ProgramaEdicion;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/ediciones/{idEdicion}/programas")
public class ProgramaEdicionController {

    @Inject
    private ProgramaEdicionService programaEdicionService;

    @ApiOperation(nickname = "Listar programas edicion ", value = "Lista todos los programas de una edicion")
    @RequestMapping(method = RequestMethod.GET)
    public Collection<ProgramaEdicion> getAll(@PathVariable Long idEdicion) {
        return programaEdicionService.listar(idEdicion);
    }

    @ApiOperation(nickname = "Obtener programa edicion", value = "Obtiene un programa asociado a una edicion")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ProgramaEdicion get(@PathVariable Long id) {
        return programaEdicionService.get(id);
    }

    @ApiOperation(nickname = "Crear programa edicion", value = "Crea un programa asociado a una edicion")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<ProgramaEdicion> create(@RequestBody ProgramaEdicion programaEdicion) {
        this.programaEdicionService.crear(programaEdicion);
        return new ResponseEntity<ProgramaEdicion>(programaEdicion, HttpStatus.CREATED);
    }

    @ApiOperation(nickname = "Actualizar programa edicion", value = "Actualiza un programa para una edicion")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<ProgramaEdicion> update(@PathVariable Long id, @RequestBody ProgramaEdicion p) {
        this.programaEdicionService.actualizar(id, p);
        return ResponseEntity.ok(p);
    }

    @ApiOperation(nickname = "Borrar programa edicion", value = "Borra un programa en una edicion")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        this.programaEdicionService.borrar(id);
    }

}
