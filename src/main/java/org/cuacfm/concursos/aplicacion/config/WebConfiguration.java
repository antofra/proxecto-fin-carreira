package org.cuacfm.concursos.aplicacion.config;

import java.util.Arrays;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.ServletContextInitializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class WebConfiguration implements ServletContextInitializer, EmbeddedServletContainerCustomizer {

    private static final Logger logger = LoggerFactory.getLogger(WebConfiguration.class);

    @Inject
    private Environment env;

    @Override
    public void customize(ConfigurableEmbeddedServletContainer container) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        logger.info("Web application configuration, using profiles: {}", Arrays.toString(env.getActiveProfiles()));
    }

}
