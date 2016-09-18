package org.cuacfm.concursos.modelo.entidad.util;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.cuacfm.concursos.api.AuthorityService;
import org.cuacfm.concursos.api.CategoriaService;
import org.cuacfm.concursos.api.ConcursoService;
import org.cuacfm.concursos.api.IntegranteService;
import org.cuacfm.concursos.api.NoAsociadaService;
import org.cuacfm.concursos.api.PersonaAsociadaService;
import org.cuacfm.concursos.api.ProgramaService;
import org.cuacfm.concursos.api.RolIntegranteService;
import org.cuacfm.concursos.api.UsuarioService;
import org.cuacfm.concursos.aplicacion.seguridad.AuthoritiesConstants;
import org.cuacfm.concursos.modelo.entidad.concurso.Categoria;
import org.cuacfm.concursos.modelo.entidad.concurso.Concurso;
import org.cuacfm.concursos.modelo.entidad.maestro.Integrante;
import org.cuacfm.concursos.modelo.entidad.maestro.NoAsociada;
import org.cuacfm.concursos.modelo.entidad.maestro.PersonaAsociada;
import org.cuacfm.concursos.modelo.entidad.maestro.Programa;
import org.cuacfm.concursos.modelo.entidad.maestro.RolIntegrante;
import org.cuacfm.concursos.modelo.entidad.seguridad.Usuario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class IniciarBDUtil {
    private Logger logger = LoggerFactory.getLogger(IniciarBDUtil.class);

    @Inject
    private UsuarioService usuarioService;

    @Inject
    private AuthorityService authorityService;

    @Inject
    private ConcursoService concursoService;

    @Inject
    private CategoriaService categoriaService;

    @Inject
    private NoAsociadaService noAsociadaService;

    @Inject
    private ProgramaService programaService;

    @Inject
    private PersonaAsociadaService personaAsociadaService;

    @Inject
    private RolIntegranteService rolIntegranteService;

    @Inject
    private IntegranteService integranteService;

    @PostConstruct
    public void init() throws JsonParseException, JsonMappingException, IOException {

        if (authorityService.get(AuthoritiesConstants.USER) == null) {
            authorityService.crear(AuthoritiesConstants.USER);
        }
        if (authorityService.get(AuthoritiesConstants.ADMIN) == null) {
            authorityService.crear(AuthoritiesConstants.ADMIN);
        }

        if (!usuarioService.get("admin").isPresent()) {
            Usuario usuario = new Usuario();
            usuario.setLogin("admin");
            usuario.setAuthorities(Collections.singleton(authorityService.get(AuthoritiesConstants.ADMIN)));
            logger.debug("Creando admin...");
            usuarioService.crearUsuario(usuario);
            iniciarBBDD();
        }
    }

    private void iniciarBBDD() throws JsonParseException, JsonMappingException, IOException {
        ObjectMapper mapper = new ObjectMapper();

        List<Concurso> concursos = mapper.readValue(ClassLoader.getSystemResourceAsStream("maestro_concursos.json"),
                new TypeReference<List<Concurso>>() {
                });
        for (Concurso concurso : concursos) {
            this.concursoService.crear(concurso);
        }

        List<Categoria> categorias = mapper.readValue(ClassLoader.getSystemResourceAsStream("maestro_categorias.json"),
                new TypeReference<List<Categoria>>() {
                });
        for (Categoria categoria : categorias) {
            this.categoriaService.crear(categoria);
        }

        List<NoAsociada> noAsociadas = mapper.readValue(
                ClassLoader.getSystemResourceAsStream("maestro_no_asociadas.json"),
                new TypeReference<List<NoAsociada>>() {
                });
        for (NoAsociada noAsociada : noAsociadas) {
            this.noAsociadaService.crear(noAsociada);
        }

        List<Programa> programas = mapper.readValue(ClassLoader.getSystemResourceAsStream("maestro_programas.json"),
                new TypeReference<List<Programa>>() {
                });

        for (Programa programa : programas) {
            this.programaService.crear(programa);

            for (Integrante i : programa.getIntegrantes()) {
                PersonaAsociada pa = this.personaAsociadaService.getByNombreApellidosAndDni(i.getAsociada().getNombre(),
                        i.getAsociada().getApellidos(), i.getAsociada().getDni());
                if (pa == null) {
                    this.personaAsociadaService.crear(i.getAsociada());
                } else {
                    i.getAsociada().setId(pa.getId());
                }
                i.setPrograma(programa);
                for (RolIntegrante rol : i.getRoles()) {
                    RolIntegrante r = this.rolIntegranteService.getByRol(rol.getRol());
                    if (r == null) {
                        this.rolIntegranteService.crear(rol);
                    } else {
                        rol.setId(r.getId());
                    }

                }
                this.integranteService.crear(programa.getId(), i);
            }
        }
    }

}
