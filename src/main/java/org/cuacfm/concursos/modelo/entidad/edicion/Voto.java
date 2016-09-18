package org.cuacfm.concursos.modelo.entidad.edicion;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(schema = "EDICION", name = "VOTO", uniqueConstraints = {
        @UniqueConstraint(columnNames = { "ID_PROGRAMA_EDICION", "ID_CATEGORIA_EDICION" }) })
public class Voto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ID_PROGRAMA_EDICION", insertable = false, updatable = false)
    private ProgramaEdicion programaEdicion;

    @ManyToOne
    @JoinColumn(name = "ID_CATEGORIA_EDICION", insertable = false, updatable = false)
    private CategoriaEdicion categoriaEdicion;

    private Long tres;

    private Long dos;

    private Long uno;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProgramaEdicion getProgramaEdicion() {
        return programaEdicion;
    }

    public void setProgramaEdicion(ProgramaEdicion programaEdicion) {
        this.programaEdicion = programaEdicion;
    }

    public CategoriaEdicion getCategoriaEdicion() {
        return categoriaEdicion;
    }

    public void setCategoriaEdicion(CategoriaEdicion categoriaEdicion) {
        this.categoriaEdicion = categoriaEdicion;
    }

    public Long getTres() {
        return tres;
    }

    public void setTres(Long tres) {
        this.tres = tres;
    }

    public Long getDos() {
        return dos;
    }

    public void setDos(Long dos) {
        this.dos = dos;
    }

    public Long getUno() {
        return uno;
    }

    public void setUno(Long uno) {
        this.uno = uno;
    }

    // FIXME Esto no es del todo correcto, y parece que no vale.
    // @AssertTrue(message = "votos repetidos")
    // public boolean isValid() {
    // return !this.uno.equals(this.dos) && !this.uno.equals(this.tres) &&
    // !this.dos.equals(this.tres);
    // }
}
