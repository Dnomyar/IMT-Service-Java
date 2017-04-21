package fr.damienraymond.servicejava.tp4.server.configuration;

import javax.ws.rs.core.MediaType;

public class JAXRS {
	// Adresses
	public static final String PROJET = "http://localhost:8080/servicejava/tp4";
	
	public static final String CHEMIN_AUTOMATE = "serveur0"; 
	public static final String CHEMIN_UN = "serveur1"; 
	public static final String CHEMIN_DEUX = "serveur2"; 
	// Chemins et requêtes
	public static final String SOUS_CHEMIN_INITIAL = "automate/initial";
	public static final String SOUS_CHEMIN_SUIVANT = "automate/suivant";
	public static final String CLE_ETIQUETTE = "etiquette";
	// Types
	public static final String TYPE_MEDIA = MediaType.APPLICATION_XML;
	// XML
	public static final String BALISE_HYPERLIEN = "hyperlien";
	public static final String ATTRIBUT_HYPERLIEN = "uri";
}
