package org.cuacfm.concursos.web.controller;

import java.net.URISyntaxException;
import java.util.Collection;

import javax.inject.Inject;

import org.cuacfm.concursos.api.CategoriaEdicionService;
import org.cuacfm.concursos.modelo.entidad.concurso.Categoria;
import org.cuacfm.concursos.modelo.entidad.edicion.CategoriaEdicion;
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
@RequestMapping("/api/ediciones/{idEdicion}/categorias")
public class CategoriaEdicionController {

    @Inject
    private CategoriaEdicionService categoriaEdicionService;

    @ApiOperation(nickname = "Listar categorias edicion", value = "Lista todas las categorias de una edicion")
    @RequestMapping(method = RequestMethod.GET)
    public Collection<CategoriaEdicion> listarCategorias(@PathVariable Long idEdicion) throws URISyntaxException {
        return this.categoriaEdicionService.listar(idEdicion);
    }

    @ApiOperation(nickname = "Crear categoria edicion", value = "Crea una nueva categoria edicion")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<CategoriaEdicion> create(@PathVariable Long idEdicion, @RequestBody Categoria categoria)
            throws URISyntaxException {
        return new ResponseEntity<CategoriaEdicion>(categoriaEdicionService.crear(idEdicion, categoria),
                HttpStatus.CREATED);
    }

    @ApiOperation(nickname = "Actualizar categoria edicion", value = "Actualiza una categoria edicion")
    @RequestMapping(value = "/{idCategoriaEdicion}", method = RequestMethod.PUT)
    public ResponseEntity<CategoriaEdicion> update(@PathVariable Long idCategoriaEdicion,
            @RequestBody CategoriaEdicion ce) throws URISyntaxException {
        categoriaEdicionService.actualizar(idCategoriaEdicion, ce);
        return ResponseEntity.ok(ce);
    }

    @ApiOperation(nickname = "Borrar categoria edicion", value = "Borra una categoria edicion")
    @RequestMapping(value = "/{idCategoriaEdicion}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long idCategoriaEdicion) {
        categoriaEdicionService.borrar(idCategoriaEdicion);
    }

    @ApiOperation(nickname = "Anadir participante edicion categoria", value = "Anade participantes en una edicion de una categoria")
    @RequestMapping(value = "/{idCategoria}/participantes", method = RequestMethod.POST)
    public void crear(@PathVariable Long idEdicion, @PathVariable Long idCategoria,
            @RequestBody Collection<ParticipanteEdicion> participantes) throws URISyntaxException {
        // this.categoriaEdicionService.anadirParticipantes(idEdicion,
        // idCategoria, participantes);
    }

}
