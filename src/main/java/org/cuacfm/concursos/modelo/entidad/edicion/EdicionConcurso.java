package org.cuacfm.concursos.modelo.entidad.edicion;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.cuacfm.concursos.modelo.entidad.concurso.Concurso;
import org.joda.time.DateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
@Table(schema = "EDICION", name = "EDICION")
public class EdicionConcurso {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_EDICION_CONCURSO")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ID_CONCURSO")
    @NotNull
    @JsonProperty(access = Access.WRITE_ONLY)
    private Concurso concurso;

    @Column
    private Date fechaCelebracion;

    @Column
    private Date fechaHoraPublicacion;

    @Column
    private Date fechaHoraInicioVotaciones;

    @Column
    private Date fechaHoraFinVotaciones;

    @Column(nullable = false)
    private String descripcion;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "edicion", orphanRemoval = true, cascade = CascadeType.ALL)
    private Set<CategoriaEdicion> categoriasEdicion = new HashSet<CategoriaEdicion>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "edicion", targetEntity = ParticipanteEdicion.class, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ProgramaEdicion> programas = new HashSet<ProgramaEdicion>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Concurso getConcurso() {
        return concurso;
    }

    public void setConcurso(Concurso concurso) {
        this.concurso = concurso;
    }

    public Date getFechaCelebracion() {
        return fechaCelebracion;
    }

    public void setFechaCelebracion(Date fechaCelebracion) {
        this.fechaCelebracion = fechaCelebracion;
    }

    public Date getFechaHoraPublicacion() {
        return fechaHoraPublicacion;
    }

    public void setFechaHoraPublicacion(Date fechaHoraPublicacion) {
        this.fechaHoraPublicacion = fechaHoraPublicacion;
    }

    public Date getFechaHoraInicioVotaciones() {
        return fechaHoraInicioVotaciones;
    }

    public void setFechaHoraInicioVotaciones(Date fechaHoraInicioVotaciones) {
        this.fechaHoraInicioVotaciones = fechaHoraInicioVotaciones;
    }

    public Date getFechaHoraFinVotaciones() {
        return fechaHoraFinVotaciones;
    }

    public void setFechaHoraFinVotaciones(Date fechaHoraFinVotaciones) {
        this.fechaHoraFinVotaciones = fechaHoraFinVotaciones;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Set<CategoriaEdicion> getCategoriasEdicion() {
        return categoriasEdicion;
    }

    public void setCategoriasEdicion(Set<CategoriaEdicion> categoriasEdicion) {
        for (CategoriaEdicion categoriaEdicion : categoriasEdicion) {
            categoriaEdicion.setEdicion(this);
            for (ParticipanteEdicion pe : categoriaEdicion.getParticipantes()) {
                pe.setEdicion(this);
            }
        }

        this.categoriasEdicion = categoriasEdicion;
    }

    public void updateCategoriasEdicion(Set<CategoriaEdicion> categoriasEdicion) {

        Set<CategoriaEdicion> eliminadas = new HashSet<>();

        // Se eliminan las categorias que ya no vienen en el nuevo conjunto (se
        // marca el padre a nulo)
        boolean encontrada = false;
        for (CategoriaEdicion ce : this.getCategoriasEdicion()) {
            encontrada = false;

            for (CategoriaEdicion categoriaEdicion : categoriasEdicion) {
                if (categoriaEdicion.getCategoria().getId().equals(ce.getCategoria().getId())) {
                    ce.updateParticipantes(categoriaEdicion.getParticipantes());
                    categoriasEdicion.remove(categoriaEdicion);
                    encontrada = true;
                    break;
                }
            }

            if (!encontrada) {
                eliminadas.add(ce);
            }
        }

        this.getCategoriasEdicion().addAll(categoriasEdicion);
        this.getCategoriasEdicion().removeAll(eliminadas);
    }

    public Set<ProgramaEdicion> getProgramas() {
        return programas;
    }

    public void setProgramas(Set<ProgramaEdicion> programas) {
        for (ProgramaEdicion pe : programas) {
            pe.setEdicion(this);
            for (IntegranteEdicion ie : pe.getIntegrantes()) {
                ie.setEdicion(this);
                ie.setProgramaEdicion(pe);
            }
        }
        this.programas = programas;
    }

    public void updateProgramasEdicion(Set<ProgramaEdicion> programas) {
        Set<ProgramaEdicion> eliminados = new HashSet<>();

        boolean encontrado = false;
        for (ProgramaEdicion pe : this.getProgramas()) {
            encontrado = false;

            for (ProgramaEdicion programaEdicion : programas) {
                if (programaEdicion.getPrograma().getId().equals(pe.getPrograma().getId())) {
                    pe.updateIntegrantes(programaEdicion.getIntegrantes());
                    programas.remove(programaEdicion);
                    encontrado = true;
                    break;
                }
            }

            if (!encontrado) {
                eliminados.add(pe);
            }
        }

        this.getProgramas().addAll(programas);
        this.getProgramas().removeAll(eliminados);
    }

    public boolean isActiva() {
        DateTime dt = new DateTime();
        return ((this.fechaHoraPublicacion != null && this.fechaHoraPublicacion.before(dt.toDate()))
                && (this.fechaHoraFinVotaciones != null && this.fechaHoraFinVotaciones.before(dt.toDate())));
    }

}
