package fr.damienraymond.servicejava.tp4.server.modele;

import fr.damienraymond.servicejava.tp4.server.configuration.JAXRS;
import fr.damienraymond.servicejava.tp4.server.infrastructure.jaxrs.HyperLien;
import fr.damienraymond.servicejava.tp4.server.infrastructure.jaxrs.annotations.ReponsesNull404GET;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

public interface Automate {

	@GET
	@ReponsesNull404GET
	@Path(value = JAXRS.SOUS_CHEMIN_INITIAL)
	@Produces(JAXRS.TYPE_MEDIA)
	HyperLien<EtatAutomate> initier();
}
