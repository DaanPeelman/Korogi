package com.korogi.rest;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import com.korogi.core.config.CoreConfig;
import com.korogi.core.config.PersistenceConfig;
import com.korogi.rest.config.RestConfig;
import com.korogi.rest.config.SecurityConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class RestInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    private static final String SERVLET_NAME = "Korogi - Rest";

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        super.onStartup(servletContext);
        servletContext.addListener(new HttpSessionEventPublisher());
    }

    @Override
    protected String getServletName() {
        return SERVLET_NAME;
    }

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] { RestApplicationContext.class };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[0];
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }

    @Configuration
    @Import(
            value = {
                    CoreConfig.class,
                    PersistenceConfig.class,
                    SecurityConfig.class,
                    RestConfig.class
            }
    )
    static class RestApplicationContext {
    }
}