package fr.damienraymond.servicejava.tp3.server.modele;

import fr.damienraymond.servicejava.tp3.server.infrastructure.jaxrs.HyperLien;
import fr.damienraymond.servicejava.tp3.server.infrastructure.jaxrs.HyperLiens;

public interface Bibliotheque {
    HyperLien<LivreRessource> ajouter(Livre l);

    HyperLien<LivreRessource> chercher(Livre l);

    HyperLiens<LivreRessource> repertorier();

}
