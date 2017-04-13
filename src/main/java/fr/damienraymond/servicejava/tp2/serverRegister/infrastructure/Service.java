package fr.damienraymond.servicejava.tp2.serverRegister.infrastructure;

import fr.damienraymond.servicejava.tp2.serverRegister.modele.Registre;
import fr.damienraymond.servicejava.tp2.serverRegister.modele.Ressource;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.logging.LoggingFeature;
import org.glassfish.jersey.server.ResourceConfig;


public class Service extends ResourceConfig {
    public Service() {
        System.out.println("* Chargement de " + this.getClass() + " ...");
        System.out.println("** Configuration");
        this.register(LoggingFeature.class);
        this.property(LoggingFeature.LOGGING_FEATURE_LOGGER_LEVEL_SERVER, "INFO");
        this.register(JacksonFeature.class);

        System.out.println("** Ressource");
        // Initialisation de la ressource
        Ressource r = Ressource.SINGLETON;

        System.out.println("** Registre ");
        // Initialisation et enregistrement du service
        Registre registre = new Registre(r);
        this.register(registre);

    }
}

