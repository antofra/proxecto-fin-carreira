package org.cuacfm.concursos.web.controller;

import java.util.Collection;

import javax.inject.Inject;

import org.cuacfm.concursos.api.CategoriaService;
import org.cuacfm.concursos.modelo.entidad.concurso.Categoria;
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
@RequestMapping("/api/categorias")
@Secured("ROLE_ADMIN")
public class CategoriaController {

    @Inject
    private CategoriaService categoriaService;

    @ApiOperation(nickname = "Listar categorias", value = "Lista todas las categorias")
    @RequestMapping(method = RequestMethod.GET)
    public Collection<Categoria> getAll(@RequestParam(required = false) String nombre) {
        return categoriaService.listar(nombre);
    }

    @ApiOperation(nickname = "Obtener categoria", value = "Obtiene una categoria")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Categoria> get(@PathVariable Long id) {
        return new ResponseEntity<Categoria>(categoriaService.get(id), HttpStatus.OK);
    }

    @ApiOperation(nickname = "Crear categoria", value = "Crea una nueva categoria")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Categoria> create(@RequestBody Categoria c) {
        categoriaService.crear(c);
        return new ResponseEntity<Categoria>(c, HttpStatus.CREATED);
    }

    @ApiOperation(nickname = "Actualizar categoria", value = "Actualiza una categoria")
    @RequestMapping(value = "/{idCategoria}", method = RequestMethod.PUT)
    public ResponseEntity<Categoria> update(@PathVariable Long idCategoria, @RequestBody Categoria c) {
        categoriaService.actualizar(idCategoria, c);
        return ResponseEntity.ok(c);
    }

    @ApiOperation(nickname = "Borrar categoria", value = "Borra una categoria")
    @RequestMapping(value = "/{idCategoria}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long idCategoria) {
        categoriaService.borrar(idCategoria);
    }

}
