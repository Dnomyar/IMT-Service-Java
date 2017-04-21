package fr.damienraymond.servicejava.tp3.client.client;


import fr.damienraymond.servicejava.tp3.client.configuration.JAXRS;
import fr.damienraymond.servicejava.tp3.client.infrastructure.jaxrs.HyperLien;
import fr.damienraymond.servicejava.tp3.client.modele.Bibliotheque;
import fr.damienraymond.servicejava.tp3.client.modele.ImplemLivre;
import fr.damienraymond.servicejava.tp3.client.modele.Livre;
import fr.damienraymond.servicejava.tp3.client.modele.LivreRessource;
import org.glassfish.jersey.client.proxy.WebResourceFactory;

import javax.ws.rs.client.WebTarget;

import java.util.stream.Stream;

import static fr.damienraymond.servicejava.tp3.configuration.Config.BASE_URL;

public class ClientConcurrence {
    private static final String ADRESSE = BASE_URL + "/" + JAXRS.CHEMIN_BIBLIO;

    public static void main(String[] args) {
        System.out.println("*************");

        WebTarget cible = AppliCliente.clientJAXRS().target(ADRESSE);
        Bibliotheque biblio = WebResourceFactory.newResource(Bibliotheque.class, cible);

        System.out.println("*** 1. Ajouter des livres ***");

        Livre l1 = new ImplemLivre("Restful Java with JAX-RS");

        final int NB_ITERATION = 1000;

        final long count = Stream.iterate(0, i -> i + 1)
                .limit(NB_ITERATION)
                .parallel()
                .map(i -> biblio.ajouter(l1).getUri())
                .distinct()
                .count();


        System.out.println(String.format("Distinct URL : %d/%d", count, NB_ITERATION));
        System.exit(0);
    }

}
