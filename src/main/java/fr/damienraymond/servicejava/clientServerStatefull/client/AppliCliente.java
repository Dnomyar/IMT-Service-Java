package fr.damienraymond.servicejava.clientServerStatefull.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;


import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.logging.LoggingFeature;

import fr.damienraymond.servicejava.clientServerStatefull.rest.Automate;
import fr.damienraymond.servicejava.clientServerStatefull.rest.jaxb.FournisseurTraduction;

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
		
		String adresse = "http://localhost:8080/AutomateAvecEtatSession/automate";

		System.out.println("*************");
		
		// TODO
	
		System.out.println("*************");
		
	}

	private static void test(Automate automate) {
		char[] mot = { 'a', 'b', 'a', 'a', 'a', 'b' };
		// TODO
	}

}
