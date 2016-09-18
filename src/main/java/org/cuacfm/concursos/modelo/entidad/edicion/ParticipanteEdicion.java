package org.cuacfm.concursos.modelo.entidad.edicion;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(schema = "EDICION", name = "PARTICIPANTE_EDICION")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "tipo")
@JsonSubTypes({ @Type(value = ProgramaEdicion.class), @Type(value = IntegranteEdicion.class),
        @Type(value = NoAsociadaEdicion.class) })
public abstract class ParticipanteEdicion implements IParticipante {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_PARTICIPANTE_EDICION")
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "ID_EDICION_CONCURSO")
    @NotNull
    @JsonProperty(access = Access.WRITE_ONLY)
    private EdicionConcurso edicion;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EdicionConcurso getEdicion() {
        return edicion;
    }

    public void setEdicion(EdicionConcurso edicion) {
        this.edicion = edicion;
    }

}
