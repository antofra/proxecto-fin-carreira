package org.cuacfm.concursos.aplicacion;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Arrays;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.cuacfm.concursos.aplicacion.seguridad.jwt.JWTSecurityConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.core.env.Environment;
import org.springframework.core.env.SimpleCommandLinePropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = { "org.cuacfm.concursos" })
@EnableJpaRepositories(basePackages = { "org.cuacfm.concursos.modelo.repositorio" })
@EntityScan(basePackages = { "org.cuacfm.concursos.modelo.entidad" })
@EnableConfigurationProperties({ JWTSecurityConfig.class })
public class Application {

    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    @Inject
    private Environment env;

    public static void main(String[] args) throws UnknownHostException {
        System.setProperty("org.jboss.logging.provider", "slf4j");
        SpringApplication app = new SpringApplication(Application.class);
        SimpleCommandLinePropertySource source = new SimpleCommandLinePropertySource(args);

        addDefaultProfile(app, source);

        // Environment env = app.run(args).getEnvironment();
        app.run(args);
    }

    @PostConstruct
    public void initApplication() throws IOException {
        if (env.getActiveProfiles().length == 0)
            logger.warn("No Spring profile configured, running with default configuration");
        else
            logger.info("Running with Spring profile(s) : {}", Arrays.toString(env.getActiveProfiles()));
    }

    private static void addDefaultProfile(SpringApplication app, SimpleCommandLinePropertySource source) {
        if (!source.containsProperty("spring.profiles.active"))
            app.setAdditionalProfiles("dev");
    }
}
