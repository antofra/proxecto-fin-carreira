package org.cuacfm.concursos.modelo.entidad.edicion;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.cuacfm.concursos.modelo.entidad.maestro.Integrante;
import org.cuacfm.concursos.modelo.entidad.maestro.RolIntegrante;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
@PrimaryKeyJoinColumn(name = "ID_INTEGRANTE_EDICION", referencedColumnName = "ID_PARTICIPANTE_EDICION")
@Table(schema = "EDICION", name = "INTEGRANTE_EDICION")
public class IntegranteEdicion extends ParticipanteEdicion {

    @OneToOne
    @JoinColumn(name = "ID_INTEGRANTE")
    private Integrante integrante;

    @ManyToOne
    @JoinColumn(name = "ID_PROGRAMA_EDICION", insertable = false, updatable = false)
    @JsonProperty(access = Access.WRITE_ONLY)
    private ProgramaEdicion programaEdicion;

    @ManyToMany
    @JoinTable(schema = "EDICION", name = "INTEGRANTE_EDICION_ROL", joinColumns = @JoinColumn(name = "ID_INTEGRANTE_EDICION", referencedColumnName = "ID_PARTICIPANTE_EDICION"), inverseJoinColumns = @JoinColumn(name = "ID_ROL", referencedColumnName = "ID"))
    private Set<RolIntegrante> roles;

    public Integrante getIntegrante() {
        return integrante;
    }

    public void setIntegrante(Integrante integrante) {
        this.integrante = integrante;
    }

    public ProgramaEdicion getProgramaEdicion() {
        return programaEdicion;
    }

    public void setProgramaEdicion(ProgramaEdicion programaEdicion) {
        this.programaEdicion = programaEdicion;
    }

    public Set<RolIntegrante> getRoles() {
        return roles;
    }

    public void setRoles(Set<RolIntegrante> roles) {
        this.roles = roles;
    }

    @Override
    public String getNombre() {
        return String.format("%s (%s)", integrante.getAsociada().getNombre(),
                programaEdicion.getPrograma().getNombre());
    }
}
