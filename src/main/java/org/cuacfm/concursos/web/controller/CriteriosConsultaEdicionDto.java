package org.cuacfm.concursos.web.controller;

public class CriteriosConsultaEdicionDto {

    private Long idConcurso;
    private boolean ultimaEdicionPublicadaByConcurso;
    private boolean ultimaEdicionFinalizadaByConcurso;

    public Long getIdConcurso() {
        return idConcurso;
    }

    public void setIdConcurso(Long idConcurso) {
        this.idConcurso = idConcurso;
    }

    public boolean isUltimaEdicionPublicadaByConcurso() {
        return ultimaEdicionPublicadaByConcurso;
    }

    public void setUltimaEdicionPublicadaByConcurso(boolean ultimaEdicionPublicadaByConcurso) {
        this.ultimaEdicionPublicadaByConcurso = ultimaEdicionPublicadaByConcurso;
    }

    public boolean isUltimaEdicionFinalizadaByConcurso() {
        return ultimaEdicionFinalizadaByConcurso;
    }

    public void setUltimaEdicionFinalizadaByConcurso(boolean ultimaEdicionFinalizadaByConcurso) {
        this.ultimaEdicionFinalizadaByConcurso = ultimaEdicionFinalizadaByConcurso;
    }

}
