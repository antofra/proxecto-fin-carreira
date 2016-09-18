package org.cuacfm.concursos.modelo.entidad.maestro;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.cuacfm.concursos.modelo.entidad.seguridad.Usuario;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(schema = "MAESTRO", name = "PROGRAMA", uniqueConstraints = @UniqueConstraint(columnNames = { "NOMBRE" }))
public class Programa {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true)
    @NotNull
    private String nombre;

    @Column(nullable = false)
    @NotNull
    private String direccionCorreo;

    @OneToMany(mappedBy = "programa", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Set<Integrante> integrantes;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, optional = true)
    @JoinColumn(name = "ID_USUARIO")
    @JsonIgnore
    private Usuario usuario;

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

    public String getDireccionCorreo() {
        return direccionCorreo;
    }

    public void setDireccionCorreo(String direccionCorreo) {
        this.direccionCorreo = direccionCorreo;
    }

    public Set<Integrante> getIntegrantes() {
        return integrantes;
    }

    public void setIntegrantes(Set<Integrante> integrantes) {
        this.integrantes = integrantes;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}
