package org.cuacfm.concursos.web.controller;

import java.util.Collection;

import javax.inject.Inject;

import org.cuacfm.concursos.api.EdicionConcursoService;
import org.cuacfm.concursos.modelo.entidad.edicion.EdicionConcurso;
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
@RequestMapping("/api/ediciones")
@Secured({ "ROLE_ADMIN", "ROLE_USER" })
public class EdicionConcursoController {

    @Inject
    private EdicionConcursoService edicionConcursoService;

    @ApiOperation(nickname = "Listar ediciones", value = "Lista todas las ediciones")
    @RequestMapping(method = RequestMethod.GET)
    public Collection<EdicionConcurso> listar(CriteriosConsultaEdicionDto c) {
        return this.edicionConcursoService.listar(c);
    }

    @ApiOperation(nickname = "Obtiene edicion", value = "Obtiene una edicion de un concurso")
    @RequestMapping(value = "/{idEdicion}", method = RequestMethod.GET)
    public EdicionConcurso get(@PathVariable Long idEdicion) {
        return this.edicionConcursoService.get(idEdicion);
    }

    @ApiOperation(nickname = "Crear edicion", value = "Crea una nueva edicion")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<EdicionConcurso> crear(@RequestBody EdicionConcurso edicionConcurso) {
        this.edicionConcursoService.crear(edicionConcurso);
        return new ResponseEntity<EdicionConcurso>(edicionConcurso, HttpStatus.CREATED);
    }

    @ApiOperation(nickname = "Actualizar edicion", value = "Actualiza una edicion")
    @RequestMapping(value = "/{idEdicion}", method = RequestMethod.PUT)
    public ResponseEntity<EdicionConcurso> actualizar(@PathVariable Long idEdicion,
            @RequestBody EdicionConcurso edicionConcurso) {
        this.edicionConcursoService.actualizar(idEdicion, edicionConcurso);
        return ResponseEntity.ok(edicionConcurso);
    }

    @ApiOperation(nickname = "Eliminar edicion", value = "Elimina una edicion")
    @RequestMapping(value = "/{idEdicion}", method = RequestMethod.DELETE)
    public void eliminar(@PathVariable Long idEdicion) {
        this.edicionConcursoService.borrar(idEdicion);
    }

}
