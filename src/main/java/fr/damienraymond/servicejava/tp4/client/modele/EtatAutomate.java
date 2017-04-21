package fr.damienraymond.servicejava.tp4.client.modele;

import fr.damienraymond.servicejava.tp4.client.configuration.JAXRS;
import fr.damienraymond.servicejava.tp4.client.infrastructure.jaxrs.HyperLien;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

public interface EtatAutomate {

	@GET
	@Path(value = JAXRS.SOUS_CHEMIN_SUIVANT)
	@Produces(JAXRS.TYPE_MEDIA)
	HyperLien<EtatAutomate> accepter(@QueryParam(value = JAXRS.CLE_ETIQUETTE) char x);
}
