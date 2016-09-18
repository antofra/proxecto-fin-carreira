package org.cuacfm.concursos.api.impl;

import java.util.Collection;
import java.util.Optional;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.cuacfm.concursos.api.EdicionConcursoService;
import org.cuacfm.concursos.api.VotoCategoriaDto;
import org.cuacfm.concursos.api.VotoEdicionDto;
import org.cuacfm.concursos.api.VotoService;
import org.cuacfm.concursos.modelo.entidad.edicion.CategoriaEdicion;
import org.cuacfm.concursos.modelo.entidad.edicion.EdicionConcurso;
import org.cuacfm.concursos.modelo.entidad.edicion.ProgramaEdicion;
import org.cuacfm.concursos.modelo.entidad.edicion.Voto;
import org.cuacfm.concursos.modelo.entidad.seguridad.Usuario;
import org.cuacfm.concursos.modelo.repositorio.ProgramaEdicionRepository;
import org.cuacfm.concursos.modelo.repositorio.UsuarioRepository;
import org.cuacfm.concursos.modelo.repositorio.VotoRepository;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class VotoServiceImpl implements VotoService {

    private final static Logger log = Logger.getLogger(VotoService.class);

    @Inject
    private EdicionConcursoService edicionConcursoService;

    @Inject
    private UsuarioRepository usuarioRepository;

    @Inject
    private VotoRepository votoRepository;

    @Inject
    private ProgramaEdicionRepository programaEdicionRepository;

    @Override
    public Collection<Voto> findByCategoriaEdicion(Long idCategoriaEdicion) {
        return this.votoRepository.findByCategoriaEdicion(idCategoriaEdicion);
    }

    @Override
    public void emitirVoto(String login, VotoEdicionDto votoEdicion) {
        // TODO Validar datos de voto
        log.error("Emitir voto : " + login + ", " + votoEdicion);

        DateTime currentTime = new DateTime();
        EdicionConcurso edicionConcurso = this.edicionConcursoService.get(votoEdicion.getIdEdicion());

        Voto v = null;
        Optional<Usuario> usuario = this.usuarioRepository.findByLogin(login);

        if (usuario.isPresent()) {
            ProgramaEdicion pe = this.programaEdicionRepository.findByIdEdicionAndIdPrograma(votoEdicion.getIdEdicion(),
                    usuario.get().getPrograma().getId());

            for (CategoriaEdicion ce : edicionConcurso.getCategoriasEdicion()) {
                for (VotoCategoriaDto vc : votoEdicion.getVotos()) {
                    if (ce.getId().equals(vc.getIdCategoriaEdicion())) {
                        v = new Voto();
                        v.setProgramaEdicion(pe);
                        v.setCategoriaEdicion(ce);
                        v.setUno(vc.getUno());
                        v.setDos(vc.getDos());
                        v.setTres(vc.getTres());

                        log.error("Emitir voto. Guardando...");
                        this.votoRepository.save(v);
                        break;
                    }
                }
            }
        }
    }

}
