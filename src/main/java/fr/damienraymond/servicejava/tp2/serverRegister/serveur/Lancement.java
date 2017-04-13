package fr.damienraymond.servicejava.tp2.serverRegister.serveur;

import fr.damienraymond.servicejava.tp2.serverRegister.infrastructure.Service;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.core.UriBuilder;
import java.net.URI;

public class Lancement {

    public static void main(String[] args) {
        final String adresse = "http://localhost:8087/Registre";

        URI baseUri = UriBuilder.fromUri(adresse).build();
        ResourceConfig config = new Service();
        HttpServer serveur = GrizzlyHttpServerFactory.createHttpServer(baseUri, config);

        System.out.println("Serveur " + serveur + " de la classe " + serveur.getClass());
        System.out.println("Serveur Grizzly démarré : " + serveur.isStarted());
    }

}
