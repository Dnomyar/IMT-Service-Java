package modele;

import java.util.concurrent.Future;

import javax.ws.rs.*;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;

import configuration.JAXRS;
import infrastructure.jaxrs.HyperLien;
import infrastructure.jaxrs.HyperLiens;
import infrastructure.jaxrs.annotations.ReponsesNull404GET;

public interface Bibliotheque {

	@GET
	@ReponsesNull404GET
	@Produces(JAXRS.TYPE_MEDIA)
	HyperLien<LivreRessource> chercher(@QueryParam(JAXRS.CLE_TITRE) final Livre l);

	// Version asynchrone
	@GET
	@ReponsesNull404GET
	@Path(JAXRS.SOUSCHEMIN_ASYNC)
	@Produces(JAXRS.TYPE_MEDIA)
	public Future<HyperLien<LivreRessource>> chercherAsynchrone(
			@QueryParam(JAXRS.CLE_TITRE) final Livre l,
			@Suspended final AsyncResponse ar
	);

	@GET
	@Path(JAXRS.SOUSCHEMIN_CATALOGUE)
	@Produces(JAXRS.TYPE_MEDIA)
	HyperLiens<LivreRessource> repertorier();

}
