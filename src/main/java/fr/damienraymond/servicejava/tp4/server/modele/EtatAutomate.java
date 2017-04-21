package fr.damienraymond.servicejava.tp4.server.modele;

import fr.damienraymond.servicejava.tp4.server.configuration.JAXRS;
import fr.damienraymond.servicejava.tp4.server.infrastructure.jaxrs.HyperLien;
import fr.damienraymond.servicejava.tp4.server.infrastructure.jaxrs.annotations.ReponsesNull404GET;

import javax.ws.rs.*;

public interface EtatAutomate {

	@GET
	@ReponsesNull404GET
	@Path(value = JAXRS.SOUS_CHEMIN_SUIVANT)
	@Produces(JAXRS.TYPE_MEDIA)
	HyperLien<EtatAutomate> accepter(@QueryParam(value = JAXRS.CLE_ETIQUETTE) char x);
}
