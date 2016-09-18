package org.cuacfm.concursos.modelo.entidad.edicion;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.cuacfm.concursos.modelo.entidad.maestro.NoAsociada;

@Entity
@PrimaryKeyJoinColumn(name = "ID_NO_ASOCIADA_EDICION", referencedColumnName = "ID_PARTICIPANTE_EDICION")
@Table(schema = "EDICION", name = "NO_ASOCIADA_EDICION")
public class NoAsociadaEdicion extends ParticipanteEdicion {

    @ManyToOne
    @JoinColumn(name = "ID_NO_ASOCIADA")
    private NoAsociada noAsociada;

    public NoAsociada getNoAsociada() {
        return noAsociada;
    }

    public void setNoAsociada(NoAsociada noAsociada) {
        this.noAsociada = noAsociada;
    }

    @Override
    public String getNombre() {
        return noAsociada.getNombre();
    }
}
