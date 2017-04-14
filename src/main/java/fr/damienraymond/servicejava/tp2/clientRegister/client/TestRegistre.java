package fr.damienraymond.servicejava.tp2.clientRegister.client;

import fr.damienraymond.servicejava.tp2.clientRegister.modele.Ressource;
import fr.damienraymond.servicejava.tp2.clientRegister.modele.ServiceRegistre;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.proxy.WebResourceFactory;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.logging.LoggingFeature;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import java.util.stream.Stream;

import static fr.damienraymond.servicejava.tp2.Config.BASE_URL;

public class TestRegistre {

    public static Client clientJAXRS() {
        ClientConfig config = new ClientConfig();
        config.register(LoggingFeature.class);
        config.property(LoggingFeature.LOGGING_FEATURE_LOGGER_LEVEL_CLIENT, "INFO");
        config.register(JacksonFeature.class);

        return ClientBuilder.newClient(config);
    }

    public static void main(String[] args) {

        final String adresse = String.format(BASE_URL, 8080);

        WebTarget cible = clientJAXRS().target(adresse);

        final ServiceRegistre proxyRegistre = WebResourceFactory.newResource(ServiceRegistre.class, cible);


        int NB_REQUEST = 1000;


        final long count = Stream.iterate(0, i -> i + 1)
                .limit(NB_REQUEST)
                .unordered()
                .parallel()
                .map(i -> step(proxyRegistre))
                .distinct()
                .count();

        System.out.println(count + "/" + NB_REQUEST);

        System.exit(0);
    }


    private static Integer step(ServiceRegistre proxyRegistre){
        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("*** 1. Get ***").append('\n');

        Ressource s = proxyRegistre.get();
        stringBuilder.append("*** Résultat 1 : ").append(s.getI()).append('\n');

        stringBuilder.append("*** 2. Set ***").append('\n');

        s.setI(s.getI() + 1);
        s = proxyRegistre.set(s);

        stringBuilder.append("*** Résultat 2 : ").append(s.getI()).append('\n');

        System.out.println(stringBuilder.toString());

        return s.getI();
    }

}
