package org.cuacfm.concursos.modelo.entidad.maestro;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(schema = "MAESTRO", name = "PERSONA_ASOCIADA")
public class PersonaAsociada {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_PERSONA_ASOCIADA")
    private Long id;

    @Column(nullable = false)
    @NotNull
    private String nombre;

    @Column(nullable = false)
    @NotNull
    private String apellidos;

    @Column(nullable = false)
    @NotNull
    private String dni;

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

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

}
