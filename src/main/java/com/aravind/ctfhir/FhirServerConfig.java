package com.aravind.ctfhir;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.server.IResourceProvider;
import ca.uhn.fhir.rest.server.RestfulServer;
import ca.uhn.fhir.rest.server.interceptor.ResponseHighlighterInterceptor;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;

import java.util.Collection;
 

@Configuration
public class FhirServerConfig {

    private final ApplicationContext applicationContext;

    public FhirServerConfig(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Bean
    public RestfulServer restfulServer(FhirContext fhirContext) {
        RestfulServer server = new RestfulServer(fhirContext);

        // Find all beans that implement IResourceProvider
        Collection<IResourceProvider> resourceProviders = applicationContext.getBeansOfType(IResourceProvider.class).values();

        // Set the resource providers
        server.setResourceProviders(new ArrayList<>(resourceProviders)); // Pass a Collection or List of IResourceProvider

        // Add interceptors (optional)
        server.registerInterceptor(new ResponseHighlighterInterceptor());

        return server;
    }

    @Bean
    public FhirContext fhirContext() {
        // Specify the FHIR version to use
        return FhirContext.forR4();
    }

    // Register the RestfulServer as a servlet
    @Bean
    public ServletRegistrationBean<RestfulServer> fhirServletRegistration(RestfulServer restfulServer) {
        ServletRegistrationBean<RestfulServer> registration = new ServletRegistrationBean<>(restfulServer, "/fhir/*"); // Map to /fhir
        registration.setName("FhirServlet");
        registration.setLoadOnStartup(1);
        return registration;
    }
}
