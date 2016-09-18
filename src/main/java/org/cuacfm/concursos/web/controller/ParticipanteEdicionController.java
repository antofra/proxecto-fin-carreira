package org.cuacfm.concursos.web.controller;

import java.util.Collection;

import javax.inject.Inject;

import org.cuacfm.concursos.api.ParticipanteEdicionService;
import org.cuacfm.concursos.modelo.entidad.edicion.ParticipanteEdicion;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/ediciones/{idEdicion}/participantes")
public class ParticipanteEdicionController {

    @Inject
    private ParticipanteEdicionService participanteEdicionService;

    @ApiOperation(nickname = "Listar participantes edicion ", value = "Lista todos los participantes de una edicion")
    @RequestMapping(method = RequestMethod.GET)
    public Collection<ParticipanteEdicion> getAll(@PathVariable Long idEdicion) {
        return participanteEdicionService.listar(idEdicion);
    }

    @ApiOperation(nickname = "Obtener participante edicion", value = "Obtiene un participante asociado a una edicion")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ParticipanteEdicion get(@PathVariable Long id) {
        return participanteEdicionService.get(id);
    }

    @ApiOperation(nickname = "Crear participante edicion", value = "Crea un participante asociado a una edicion")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<ParticipanteEdicion> create(@RequestBody ParticipanteEdicion participanteEdicion) {
        this.participanteEdicionService.crear(participanteEdicion);
        return new ResponseEntity<ParticipanteEdicion>(participanteEdicion, HttpStatus.CREATED);
    }

    @ApiOperation(nickname = "Actualizar participante edicion", value = "Actualiza un participante para una edicion")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<ParticipanteEdicion> update(@PathVariable Long id, @RequestBody ParticipanteEdicion p) {
        this.participanteEdicionService.actualizar(id, p);
        return ResponseEntity.ok(p);
    }

    @ApiOperation(nickname = "Borrar participante edicion", value = "Borra un participante en una edicion")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        this.participanteEdicionService.borrar(id);
    }

}
