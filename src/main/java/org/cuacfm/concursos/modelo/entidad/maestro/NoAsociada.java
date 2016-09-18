package org.cuacfm.concursos.modelo.entidad.maestro;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

@Entity
@Table(schema = "MAESTRO", name = "NO_ASOCIADA", uniqueConstraints = @UniqueConstraint(columnNames = { "NOMBRE" }))
public class NoAsociada {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_NO_ASOCIADA")
    private Long id;

    @Column(nullable = false, unique = true)
    @NotNull
    private String nombre;

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
