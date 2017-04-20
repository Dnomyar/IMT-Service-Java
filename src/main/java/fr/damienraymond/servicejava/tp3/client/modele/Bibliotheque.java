package fr.damienraymond.servicejava.tp3.client.modele;

import fr.damienraymond.servicejava.tp3.client.infrastructure.jaxrs.HyperLien;
import fr.damienraymond.servicejava.tp3.client.infrastructure.jaxrs.HyperLiens;

public interface Bibliotheque {
    HyperLien<LivreRessource> ajouter(Livre l);

    HyperLien<LivreRessource> chercher(Livre l);

    HyperLiens<LivreRessource> repertorier();

}
