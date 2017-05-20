package modele;

import infrastructure.jaxrs.HyperLien;
import infrastructure.jaxrs.LienVersRessource;
import infrastructure.jaxrs.Outils;

import javax.ws.rs.client.Client;
import java.util.List;
import java.util.Objects;

/**
 * Created by damien on 20/05/2017.
 */
public class RechercheAsynchroneStreamParallele extends RechercheAsynchroneAbstraite {
    @Override
    public String getNomAlgorithme() {
        return "recherche async stream 8";
    }

    @Override
    public HyperLien<LivreRessource> chercher(Livre l, List<HyperLien<BibliothequeArchive>> bibliotheques, Client client) {
        return bibliotheques.stream()
                .parallel()
                .map(bibliotheque -> rechercheAsync(bibliotheque, l, client))
                .map(Outils::remplirPromesse)
                .filter(Objects::nonNull)
                .findAny()
                .orElse(null);
    }
}
