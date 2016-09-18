package org.cuacfm.concursos.api;

public class ResultadoParticipanteCategoriaEdicionDto implements Comparable<ResultadoParticipanteCategoriaEdicionDto> {

    private Long idParticipante;
    private String participante;
    private Short veces3puntos;
    private Short veces2puntos;
    private Short veces1punto;

    public Long getIdParticipante() {
        return idParticipante;
    }

    public void setIdParticipante(Long idParticipante) {
        this.idParticipante = idParticipante;
    }

    public String getParticipante() {
        return participante;
    }

    public void setParticipante(String participante) {
        this.participante = participante;
    }

    public Integer getTotalPuntos() {
        return this.veces3puntos * 3 + this.veces2puntos * 2 + this.veces1punto;
    }

    public Short getVeces3puntos() {
        return veces3puntos;
    }

    public void setVeces3puntos(Short veces3puntos) {
        this.veces3puntos = veces3puntos;
    }

    public Short getVeces2puntos() {
        return veces2puntos;
    }

    public void setVeces2puntos(Short veces2puntos) {
        this.veces2puntos = veces2puntos;
    }

    public Short getVeces1punto() {
        return veces1punto;
    }

    public void setVeces1punto(Short veces1punto) {
        this.veces1punto = veces1punto;
    }

    @Override
    public int compareTo(ResultadoParticipanteCategoriaEdicionDto o) {
        return this.getTotalPuntos().compareTo(o.getTotalPuntos());
    }

}
