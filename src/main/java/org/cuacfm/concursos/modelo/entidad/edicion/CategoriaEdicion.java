package org.cuacfm.concursos.modelo.entidad.edicion;

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
import javax.persistence.UniqueConstraint;

import org.cuacfm.concursos.modelo.entidad.concurso.Categoria;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
@Table(schema = "EDICION", name = "CATEGORIA_EDICION", uniqueConstraints = @UniqueConstraint(columnNames = {
        "ID_CATEGORIA", "ID_EDICION_CONCURSO" }))
public class CategoriaEdicion {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_CATEGORIA_EDICION")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ID_CATEGORIA", nullable = false, updatable = false)
    private Categoria categoria;

    @ManyToOne(optional = false)
    @JoinColumn(name = "ID_EDICION_CONCURSO")
    @JsonProperty(access = Access.WRITE_ONLY)
    private EdicionConcurso edicion;

    @ManyToMany
    @JoinTable(schema = "EDICION", name = "CATEGORIA_EDICION_PARTICIPANTE_EDICION", joinColumns = @JoinColumn(name = "ID_CATEGORIA_EDICION", referencedColumnName = "ID_CATEGORIA_EDICION"), inverseJoinColumns = @JoinColumn(name = "ID_PARTICIPANTE_EDICION", referencedColumnName = "ID_PARTICIPANTE_EDICION"))
    private Set<ParticipanteEdicion> participantes = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public EdicionConcurso getEdicion() {
        return edicion;
    }

    public void setEdicion(EdicionConcurso edicion) {
        this.edicion = edicion;
    }

    public Set<ParticipanteEdicion> getParticipantes() {
        return participantes;
    }

    public void setParticipantes(Set<ParticipanteEdicion> participantes) {
        this.participantes = participantes;
    }

    public void updateParticipantes(Set<ParticipanteEdicion> participantes) {
        Set<ParticipanteEdicion> eliminados = new HashSet<>();

        boolean encontrado = false;
        for (ParticipanteEdicion pe : this.getParticipantes()) {
            encontrado = false;

            for (ParticipanteEdicion participanteEdicion : participantes) {
                if (participanteEdicion.getId().equals(pe.getId())) {
                    participantes.remove(participanteEdicion);
                    encontrado = true;
                    break;
                }
            }

            if (!encontrado) {
                eliminados.add(pe);
            }
        }

        this.getParticipantes().addAll(participantes);
        this.getParticipantes().removeAll(eliminados);
    }
}
