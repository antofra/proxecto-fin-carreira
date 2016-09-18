package org.cuacfm.concursos.modelo.entidad.maestro;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
@Table(schema = "MAESTRO", name = "INTEGRANTE")
public class Integrante {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_INTEGRANTE")
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "ID_PERSONA_ASOCIADA")
    @NotNull
    private PersonaAsociada asociada;

    @ManyToOne(optional = false)
    @JoinColumn(name = "ID_PROGRAMA")
    @NotNull
    @JsonProperty(access = Access.WRITE_ONLY)
    private Programa programa;

    @ManyToMany
    @JoinTable(schema = "MAESTRO", name = "INTEGRANTE_ROL", joinColumns = @JoinColumn(name = "ID_INTEGRANTE", referencedColumnName = "ID_INTEGRANTE"), inverseJoinColumns = @JoinColumn(name = "ID_ROL", referencedColumnName = "ID"))
    private Set<RolIntegrante> roles = new HashSet<RolIntegrante>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PersonaAsociada getAsociada() {
        return asociada;
    }

    public void setAsociada(PersonaAsociada asociada) {
        this.asociada = asociada;
    }

    public Programa getPrograma() {
        return programa;
    }

    public void setPrograma(Programa programa) {
        this.programa = programa;
    }

    public Set<RolIntegrante> getRoles() {
        return roles;
    }

    public void setRoles(Set<RolIntegrante> roles) {
        this.roles = roles;
    }

}
