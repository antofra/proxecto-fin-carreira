package org.cuacfm.concursos.api;

import java.util.Set;

public class VotoEdicionDto {

    private Long idEdicion;
    private Set<VotoCategoriaDto> votos;

    public Long getIdEdicion() {
        return idEdicion;
    }

    public void setIdEdicion(Long idEdicion) {
        this.idEdicion = idEdicion;
    }

    public Set<VotoCategoriaDto> getVotos() {
        return votos;
    }

    public void setVotos(Set<VotoCategoriaDto> votos) {
        this.votos = votos;
    }

}
