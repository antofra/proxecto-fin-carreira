package org.cuacfm.concursos.modelo.entidad.edicion;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.cuacfm.concursos.modelo.entidad.maestro.Programa;

@Entity
@PrimaryKeyJoinColumn(name = "ID_PROGRAMA_EDICION", referencedColumnName = "ID_PARTICIPANTE_EDICION")
@Table(schema = "EDICION", name = "PROGRAMA_EDICION")
public class ProgramaEdicion extends ParticipanteEdicion {

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_PROGRAMA")
    private Programa programa;

    @OneToMany(mappedBy = "programaEdicion", fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
    private Set<IntegranteEdicion> integrantes;

    @Override
    public String getNombre() {
        return programa.getNombre();
    }

    public Programa getPrograma() {
        return programa;
    }

    public void setPrograma(Programa programa) {
        this.programa = programa;
    }

    public Set<IntegranteEdicion> getIntegrantes() {
        return integrantes;
    }

    public void setIntegrantes(Set<IntegranteEdicion> integrantes) {
        for (IntegranteEdicion ie : integrantes) {
            ie.setEdicion(this.getEdicion());
        }
        this.integrantes = integrantes;
    }

    public void updateIntegrantes(Set<IntegranteEdicion> integrantes) {
        this.getIntegrantes().clear();
        for (IntegranteEdicion ie : integrantes) {
            ie.setProgramaEdicion(this);
        }
        this.getIntegrantes().addAll(integrantes);
    }
}
