package fr.damienraymond.servicejava.tp3.client.modele;

public interface Archive {
    LivreRessource sousRessource(IdentifiantLivre id); // Une sous-ressource ne peut-être une cible de requête.

}
