package fr.damienraymond.servicejava.tp1.clientServerStateless.client;

import fr.damienraymond.servicejava.tp1.clientServerStateless.rest.jaxb.FournisseurTraduction;
import fr.damienraymond.servicejava.tp1.clientServerStateless.rest.Resultat;
import fr.damienraymond.servicejava.tp1.clientServerStateless.rest.Session;
import fr.damienraymond.servicejava.tp1.clientServerStateless.rest.Automate;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.proxy.WebResourceFactory;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.logging.LoggingFeature;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import java.util.Arrays;

public class AppliCliente {

    public static Client clientJAXRS() {
        ClientConfig config = new ClientConfig();
        config.register(LoggingFeature.class);
        config.property(LoggingFeature.LOGGING_FEATURE_LOGGER_LEVEL_CLIENT, "INFO");
        config.register(FournisseurTraduction.class);
        config.register(JacksonFeature.class);
        return ClientBuilder.newClient(config);
    }

    public static void main(String[] args) {

        String adresse = "http://localhost:8080/servicejava/serverStateless";

        System.out.println("*************");

        final WebTarget target = clientJAXRS().target(adresse);

        final Automate automate = WebResourceFactory.newResource(Automate.class, target);

        test(automate);

        System.out.println("*************");

    }

    private static void test(Automate automate) {
        Character[] mot = {'a', 'b', 'a', 'a', 'a', 'b'};

        Session session = automate.initier();

        final boolean isValid =
                Arrays.stream(mot)
                        .allMatch(e -> {
                            final Resultat accepter = automate.accepter(e, session);
                            return accepter.isValide();
                        });

        if(isValid){
            System.out.println("Automate is correct");
        }else{
            System.out.println("Automate is not correct");
        }
    }

}
