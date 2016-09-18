package org.cuacfm.concursos.modelo.entidad.concurso;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.cuacfm.concursos.modelo.entidad.edicion.EdicionConcurso;

@Entity
@Table(schema = "CONCURSO", name = "CONCURSO", uniqueConstraints = @UniqueConstraint(columnNames = { "nombre" }))
public class Concurso {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nombre;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "concurso")
    private Set<EdicionConcurso> ediciones = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Set<EdicionConcurso> getEdiciones() {
        return ediciones;
    }

    public void setEdiciones(Set<EdicionConcurso> ediciones) {
        this.ediciones = ediciones;
    }

}
