package org.cuacfm.concursos.api;

import java.util.HashSet;
import java.util.Set;

public class ResultadoCategoriaEdicionDto {

    private Long idCategoriaEdicion;
    private String categoria;
    private Set<ResultadoParticipanteCategoriaEdicionDto> resultado = new HashSet<ResultadoParticipanteCategoriaEdicionDto>();

    public Long getIdCategoriaEdicion() {
        return idCategoriaEdicion;
    }

    public void setIdCategoriaEdicion(Long idCategoriaEdicion) {
        this.idCategoriaEdicion = idCategoriaEdicion;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Set<ResultadoParticipanteCategoriaEdicionDto> getResultado() {
        return resultado;
    }

    public void setResultado(Set<ResultadoParticipanteCategoriaEdicionDto> resultado) {
        this.resultado = resultado;
    }

}
