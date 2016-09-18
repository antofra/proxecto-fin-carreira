package org.cuacfm.concursos.modelo.entidad.concurso;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(schema = "CONCURSO", name = "CATEGORIA", uniqueConstraints = @UniqueConstraint(columnNames = { "NOMBRE" }))
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_CATEGORIA")
    private Long id;

    @Column(nullable = false, unique = true, name = "NOMBRE")
    private String nombre;

    public Categoria() {
        super();
    }

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

}
