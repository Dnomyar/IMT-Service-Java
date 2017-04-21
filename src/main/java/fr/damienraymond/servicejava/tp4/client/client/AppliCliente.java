package fr.damienraymond.servicejava.tp4.client.client;

import java.util.Arrays;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import fr.damienraymond.servicejava.tp4.client.infrastructure.jaxrs.HyperLien;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.proxy.WebResourceFactory;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.logging.LoggingFeature;

import fr.damienraymond.servicejava.tp4.client.modele.Automate;
import fr.damienraymond.servicejava.tp4.client.modele.EtatAutomate;

import static fr.damienraymond.servicejava.tp4.client.configuration.JAXRS.ADRESSE_SERVEUR_AUTOMATE;

public class AppliCliente {

	private static Client clientJAXRS;

	static {
		ClientConfig config = new ClientConfig();

		config.register(LoggingFeature.class);
		config.property(LoggingFeature.LOGGING_FEATURE_LOGGER_LEVEL_CLIENT, "INFO");
		config.register(JacksonFeature.class);

		clientJAXRS = ClientBuilder.newClient(config);
	}

	private static EtatAutomate proxyEtat(HyperLien<EtatAutomate> h){
		WebTarget cible = clientJAXRS.target(h.getUri());

		return WebResourceFactory.newResource(EtatAutomate.class, cible);
	}
	
	public static void main(String[] args) {

		System.out.println("*************");

		WebTarget cible = clientJAXRS.target(ADRESSE_SERVEUR_AUTOMATE);
		final Automate automate = WebResourceFactory.newResource(Automate.class, cible);

		tester(automate, 'a', 'b', 'a', 'b', 'a', 'b');
		tester(automate, 'a', 'b', 'a', 'b', 'a', 'a');


		System.exit(0);

	}

	private static void tester(Automate automate, Character... mot) {


		try{
			HyperLien<EtatAutomate> state = automate.initier();

			for(Character c: mot){
				state = proxyEtat(state).accepter(c);
			}

			System.out.println("mot reconnu : " + Arrays.asList(mot));
		} catch(WebApplicationException e){
			System.out.println("mot non reconnu : " + Arrays.asList(mot));
		}

		// TODO
	}

}
