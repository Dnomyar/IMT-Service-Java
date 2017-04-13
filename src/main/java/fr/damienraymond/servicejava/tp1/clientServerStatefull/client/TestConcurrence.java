package fr.damienraymond.servicejava.tp1.clientServerStatefull.client;

import fr.damienraymond.servicejava.tp1.clientServerStatefull.client.AppliCliente;
import fr.damienraymond.servicejava.tp1.clientServerStatefull.rest.Automate;
import fr.damienraymond.servicejava.tp1.clientServerStatefull.rest.Session;
import org.glassfish.jersey.client.proxy.WebResourceFactory;

import javax.ws.rs.client.WebTarget;
import java.util.Collections;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by damien on 07/04/2017.
 */
public class TestConcurrence {

    public static void main(String[] args) {

        String address = "http://localhost:8080/servicejava/serverStatefull";

        System.out.println("*****************************");

        final WebTarget target = AppliCliente.clientJAXRS().target(address);
        final Automate automate = WebResourceFactory.newResource(Automate.class, target);


        int NB_REQUEST = 1000;

        final long count = Stream.iterate(0, i -> i + 1)
                .limit(NB_REQUEST)
                .unordered()
                .parallel()
                .map(i -> automate.initier().getNumero())
                .distinct()
                .count();

        System.out.println(count + "/" + NB_REQUEST);

        System.out.println("*****************************");
    }
}
