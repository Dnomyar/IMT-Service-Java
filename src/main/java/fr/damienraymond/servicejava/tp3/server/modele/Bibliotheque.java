package fr.damienraymond.servicejava.tp3.server.modele;

import fr.damienraymond.servicejava.tp3.server.configuration.JAXRS;
import fr.damienraymond.servicejava.tp3.server.infrastructure.jaxrs.HyperLien;
import fr.damienraymond.servicejava.tp3.server.infrastructure.jaxrs.HyperLiens;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.awt.*;

public interface Bibliotheque {

    @POST
    @Consumes(JAXRS.TYPE_MEDIA)
    @Produces(JAXRS.TYPE_MEDIA)
    HyperLien<LivreRessource> ajouter(Livre l);

    @GET
    @Produces(JAXRS.TYPE_MEDIA)
    HyperLien<LivreRessource> chercher(@QueryParam(value = JAXRS.CLE_TITRE) Livre l);

    @GET
    @Path(JAXRS.SOUSCHEMIN_CATALOGUE)
    @Produces(MediaType.APPLICATION_XML)
    HyperLiens<LivreRessource> repertorier();

}
