package org.cuacfm.concursos.web.controller;

import java.util.Collection;

import javax.inject.Inject;

import org.cuacfm.concursos.api.ConcursoService;
import org.cuacfm.concursos.modelo.entidad.concurso.Concurso;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/concursos")
public class ConcursoController {

    @Inject
    private ConcursoService concursoService;

    @ApiOperation(nickname = "Listar concursos", value = "Lista todos los concursos")
    @RequestMapping(method = RequestMethod.GET)
    public Collection<Concurso> listar() {
        return this.concursoService.listar();
    }

    @ApiOperation(nickname = "Obtener concurso", value = "Obtiene un concurso")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Concurso get(@PathVariable Long id) {
        return this.concursoService.get(id);
    }

    @ApiOperation(nickname = "Crear concurso", value = "Crea un nuevo concurso")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Concurso> crear(@RequestBody Concurso c) {
        this.concursoService.crear(c);
        return new ResponseEntity<Concurso>(c, HttpStatus.CREATED);
    }

    @ApiOperation(nickname = "Actualizar concurso", value = "Actualiza un concurso")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Concurso> actualizar(@PathVariable Long id, @RequestBody Concurso c) {
        this.concursoService.actualizar(id, c);
        return ResponseEntity.ok(c);
    }

    @ApiOperation(nickname = "Eliminar concurso", value = "Elimina un concurso")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void eliminar(@PathVariable Long id) {
        this.concursoService.borrar(id);
    }

}
