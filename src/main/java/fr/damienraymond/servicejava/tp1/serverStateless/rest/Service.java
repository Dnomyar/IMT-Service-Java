package fr.damienraymond.servicejava.tp1.serverStateless.rest;

import fr.damienraymond.servicejava.tp1.serverStateless.rest.jaxb.FournisseurTraduction;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.logging.LoggingFeature;
import org.glassfish.jersey.server.ResourceConfig;

public class Service extends ResourceConfig {
    public Service() {
        System.out.println("Chargement de " + this.getClass());
        this.register(LoggingFeature.class);
        this.property(LoggingFeature.LOGGING_FEATURE_LOGGER_LEVEL_SERVER, "INFO");
        this.register(FournisseurTraduction.class);
        this.register(JacksonFeature.class);
        this.register(A_B_point_Etoile.class);
    }
}
