package fr.damienraymond.servicejava.tp3.server.infrastructure;

import fr.damienraymond.servicejava.tp3.server.infrastructure.jaxrs.AdapterReponsesCreatedPOST;
import fr.damienraymond.servicejava.tp3.server.infrastructure.jaxrs.AdapterReponsesNull404GET;
import fr.damienraymond.servicejava.tp3.server.modele.ImplemBibliotheque;
import fr.damienraymond.servicejava.tp3.server.modele.ImplemLivreRessource;
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

        // Adaptateurs JAXB
        this.register(fr.damienraymond.servicejava.tp3.server.infrastructure.jaxb.FournisseurTraduction.class);


        // Ressource
        this.register(ImplemBibliotheque.class);
        // Sous-ressources
        this.register(ImplemLivreRessource.class);


        System.out.println("** Enregistrement des filtres ");
        // Enregistrement des filtres (alternative possible via providers)
        this.register(AdapterReponsesCreatedPOST.class);
        this.register(AdapterReponsesNull404GET.class);
        System.out.println("* Fin du chargement de " + this.getClass());
    }
}

