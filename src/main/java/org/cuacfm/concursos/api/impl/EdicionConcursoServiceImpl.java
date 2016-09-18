package org.cuacfm.concursos.api.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.cuacfm.concursos.api.EdicionConcursoService;
import org.cuacfm.concursos.api.ResultadoCategoriaEdicionDto;
import org.cuacfm.concursos.api.ResultadoParticipanteCategoriaEdicionDto;
import org.cuacfm.concursos.api.VotoService;
import org.cuacfm.concursos.modelo.entidad.edicion.CategoriaEdicion;
import org.cuacfm.concursos.modelo.entidad.edicion.EdicionConcurso;
import org.cuacfm.concursos.modelo.entidad.edicion.ParticipanteEdicion;
import org.cuacfm.concursos.modelo.entidad.edicion.Voto;
import org.cuacfm.concursos.modelo.repositorio.EdicionConcursoRepository;
import org.cuacfm.concursos.modelo.repositorio.VotoRepository;
import org.cuacfm.concursos.web.controller.CriteriosConsultaEdicionDto;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class EdicionConcursoServiceImpl implements EdicionConcursoService {

    private static Logger log = Logger.getLogger(EdicionConcursoService.class);

    @Inject
    private EdicionConcursoRepository edicionConcursoRepository;

    @Inject
    private VotoService votoService;

    @Inject
    private VotoRepository votoRepository;

    @Override
    public void crear(EdicionConcurso ec) {
        edicionConcursoRepository.save(ec);
    }

    @Override
    public void actualizar(Long idEdicion, EdicionConcurso ec) {
        EdicionConcurso edicionConcurso = edicionConcursoRepository.findOne(idEdicion);
        edicionConcurso.setFechaCelebracion(ec.getFechaCelebracion());
        edicionConcurso.setFechaHoraFinVotaciones(ec.getFechaHoraFinVotaciones());
        edicionConcurso.setFechaHoraInicioVotaciones(ec.getFechaHoraInicioVotaciones());
        edicionConcurso.setDescripcion(ec.getDescripcion());

        edicionConcurso.updateCategoriasEdicion(ec.getCategoriasEdicion());
        edicionConcurso.updateProgramasEdicion(ec.getProgramas());

        this.edicionConcursoRepository.save(edicionConcurso);
    }

    @Override
    public void borrar(Long idEdicion) {
        this.edicionConcursoRepository.delete(idEdicion);
    }

    @Override
    public Collection<EdicionConcurso> listar(CriteriosConsultaEdicionDto c) {

        Collection<EdicionConcurso> result = new ArrayList<>();

        if (c.isUltimaEdicionPublicadaByConcurso() || c.isUltimaEdicionFinalizadaByConcurso()) {
            Collection<EdicionConcurso> todas = this.edicionConcursoRepository.findAll();

            DateTime dt = new DateTime();
            EdicionConcurso ecTemp = null;
            Map<Long, EdicionConcurso> edicionByConcurso = new HashMap<>();
            boolean buscarFinalizada = c.isUltimaEdicionFinalizadaByConcurso();

            for (EdicionConcurso ec : todas) {
                if (ec.getFechaHoraPublicacion() != null && ec.getFechaHoraPublicacion().before(dt.toDate())) {
                    ecTemp = edicionByConcurso.get(ec.getConcurso().getId());

                    if (ecTemp == null || (ecTemp.getFechaHoraPublicacion().before(ec.getFechaHoraPublicacion())
                            && (!buscarFinalizada
                                    || ecTemp.getFechaHoraFinVotaciones().before(ec.getFechaHoraFinVotaciones())))) {
                        edicionByConcurso.put(ec.getConcurso().getId(), ec);
                    }
                }
            }

            result.addAll(edicionByConcurso.values());
        } else if (c.getIdConcurso() != null) {
            result.addAll(this.edicionConcursoRepository.findByIdConcurso(c.getIdConcurso()));
        } else {
            result.addAll(this.edicionConcursoRepository.findAll());
        }

        return result;
    }

    @Override
    public EdicionConcurso get(Long idEdicion) {
        EdicionConcurso ec = this.edicionConcursoRepository.findOne(idEdicion);
        ec.getCategoriasEdicion();

        for (CategoriaEdicion ce : ec.getCategoriasEdicion()) {
            ce.getParticipantes();
        }

        ec.getProgramas();

        return ec;
    }

    @Override
    public Set<ResultadoCategoriaEdicionDto> getResultados(Long idEdicion) {

        Set<ResultadoCategoriaEdicionDto> result = new HashSet<ResultadoCategoriaEdicionDto>();

        EdicionConcurso ec = this.edicionConcursoRepository.findOne(idEdicion);

        short veces3puntos = 0;
        short veces2puntos = 0;
        short veces1punto = 0;
        ResultadoCategoriaEdicionDto rce = null;
        ResultadoParticipanteCategoriaEdicionDto rpce = null;

        if (ec != null && ec.getCategoriasEdicion() != null) {

            for (CategoriaEdicion ce : ec.getCategoriasEdicion()) {
                rce = new ResultadoCategoriaEdicionDto();
                rce.setIdCategoriaEdicion(ce.getId());
                rce.setCategoria(ce.getCategoria().getNombre());
                rce.setResultado(new HashSet<ResultadoParticipanteCategoriaEdicionDto>());

                if (ce.getParticipantes() != null) {
                    // Se recuperan los votos de una categoria en una edicion
                    Collection<Voto> votosCategoriaEdicion = this.votoService.findByCategoriaEdicion(ce.getId());

                    log.error("Votos edicion: " + votosCategoriaEdicion.size());
                    log.error("Votos totales: " + this.votoRepository.findAll().size());
                    // Para cada participante se contabilizan sus votos en la
                    // categoria
                    for (ParticipanteEdicion pe : ce.getParticipantes()) {
                        rpce = new ResultadoParticipanteCategoriaEdicionDto();

                        veces3puntos = 0;
                        veces2puntos = 0;
                        veces1punto = 0;

                        for (Voto v : votosCategoriaEdicion) {
                            if (v.getTres().equals(pe)) {
                                veces3puntos++;
                            } else if (v.getDos().equals(pe)) {
                                veces2puntos++;
                            } else if (v.getUno().equals(pe)) {
                                veces1punto++;
                            }
                        }

                        rpce.setIdParticipante(pe.getId());
                        rpce.setParticipante(pe.getNombre());
                        rpce.setVeces3puntos(veces3puntos);
                        rpce.setVeces2puntos(veces2puntos);
                        rpce.setVeces1punto(veces1punto);

                        rce.getResultado().add(rpce);
                    }
                }

                result.add(rce);
            }
        }

        return result;
    }

}
