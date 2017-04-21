package fr.damienraymond.servicejava.tp4.client.modele;

import fr.damienraymond.servicejava.tp4.client.configuration.JAXRS;
import fr.damienraymond.servicejava.tp4.client.infrastructure.jaxrs.HyperLien;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

public interface Automate {

	@GET
	@Path(value = JAXRS.SOUS_CHEMIN_INITIAL)
	@Produces(JAXRS.TYPE_MEDIA)
	HyperLien<EtatAutomate> initier();

}
