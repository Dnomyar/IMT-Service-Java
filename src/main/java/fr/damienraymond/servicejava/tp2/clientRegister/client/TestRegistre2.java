package fr.damienraymond.servicejava.tp2.clientRegister.client;

import fr.damienraymond.servicejava.tp2.clientRegister.infrastructure.jaxrs.AjouterPrecondition;
import fr.damienraymond.servicejava.tp2.clientRegister.infrastructure.jaxrs.Cache;
import fr.damienraymond.servicejava.tp2.clientRegister.infrastructure.jaxrs.Cacher;
import fr.damienraymond.servicejava.tp2.clientRegister.infrastructure.jaxrs.ErreurPrecondition412;
import fr.damienraymond.servicejava.tp2.clientRegister.modele.Ressource;
import fr.damienraymond.servicejava.tp2.clientRegister.modele.ServiceRegistre;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.CustomProvidersFeature;
import org.glassfish.jersey.client.proxy.WebResourceFactory;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.logging.LoggingFeature;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

import static fr.damienraymond.servicejava.tp2.Config.BASE_URL;


public class TestRegistre2 {

    public static CustomProvidersFeature fournisseurFiltres() {
        List<Class<?>> filtres = new LinkedList<>();
        filtres.add(AjouterPrecondition.class);
        filtres.add(Cacher.class);
        return new CustomProvidersFeature(filtres);
    }

    public static Client clientJAXRS(ErreurPrecondition412 gestionnaire) {
        ClientConfig config = new ClientConfig();
        config.register(LoggingFeature.class);
        config.property(LoggingFeature.LOGGING_FEATURE_LOGGER_LEVEL_CLIENT, "INFO");
        config.register(JacksonFeature.class);

        Cache cache = new Cache();

        config.register(new AbstractBinder() {
            @Override
            protected void configure() {
                bind(gestionnaire).to(ErreurPrecondition412.class);
                bind(cache).to(Cache.class);
            }
        });

        config.register(fournisseurFiltres());

        return ClientBuilder.newClient(config);
    }

    public static void main(String[] args) {

        final String adresse = String.format(BASE_URL, 8080);

        ErreurPrecondition412 gestionnaire = new ErreurPrecondition412();
        WebTarget cible = clientJAXRS(gestionnaire).target(adresse);

        final ServiceRegistre proxyRegistre = WebResourceFactory.newResource(ServiceRegistre.class, cible);

        int NB_REQUEST = 1000;

        final long count = Stream.iterate(0, i -> i + 1)
                .limit(NB_REQUEST)
                .unordered()
                .parallel()
                .map(i -> step(proxyRegistre, gestionnaire))
                .distinct()
                .count();

        System.out.println(count + "/" + NB_REQUEST);
        System.out.println("Reprises de transaction : " + gestionnaire.getReprises());
        System.exit(0);
    }



    private synchronized static Integer step(ServiceRegistre proxyRegistre, ErreurPrecondition412 gestionnaire){
        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("*** 1. Get ***").append('\n');

        Ressource s = proxyRegistre.get();
        stringBuilder.append("*** Résultat 1 : ").append(s.getI()).append('\n');

        stringBuilder.append("*** 2. Set ***");

        do {
            s.setI(s.getI() + 1);
            s = proxyRegistre.set(s);
        } while (gestionnaire.erreur412());

        stringBuilder.append("*** Résultat 2 : ").append(s.getI()).append('\n');

        System.out.println(stringBuilder.toString());

        return s.getI();
    }

}
