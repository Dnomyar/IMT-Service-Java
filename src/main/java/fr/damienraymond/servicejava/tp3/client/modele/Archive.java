package fr.damienraymond.servicejava.tp3.client.modele;

import fr.damienraymond.servicejava.tp3.server.infrastructure.jaxrs.annotations.ReponsesNull404GET;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

public interface Archive {
    // Here, no HTTP method annotation because the process is delegated to the sub resource
    @Path("{id}")
    @ReponsesNull404GET
    LivreRessource sousRessource(@PathParam("id") IdentifiantLivre id); // Une sous-ressource ne peut-être une cible de requête.
}
