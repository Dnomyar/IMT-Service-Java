package fr.damienraymond.servicejava.tp3.server.modele;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

public interface Archive {

    // Here, no HTTP method annotation because the process is delegated to the sub resource
    @Path("{id}")
	LivreRessource sousRessource(@PathParam("id") IdentifiantLivre id); // Une sous-ressource ne peut-être une cible de requête.

}
