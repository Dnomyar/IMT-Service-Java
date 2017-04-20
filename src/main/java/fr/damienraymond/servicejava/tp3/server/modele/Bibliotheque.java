package modele;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import configuration.JAXRS;
import infrastructure.jaxrs.HyperLien;
import infrastructure.jaxrs.HyperLiens;
import infrastructure.jaxrs.annotations.ReponsesCreatedPOST;
import infrastructure.jaxrs.annotations.ReponsesNull404GET;

public interface Bibliotheque {
	HyperLien<LivreRessource> ajouter(Livre l);
	HyperLien<LivreRessource> chercher(Livre l);
	HyperLiens<LivreRessource> repertorier();
	
}
