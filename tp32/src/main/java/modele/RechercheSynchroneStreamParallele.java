package modele;

import infrastructure.jaxrs.HyperLien;
import infrastructure.jaxrs.LienVersRessource;

import javax.ws.rs.client.Client;
import java.util.List;
import java.util.Objects;

/**
 * Created by damien on 20/05/2017.
 */
public class RechercheSynchroneStreamParallele extends RechercheSynchroneAbstraite {
    @Override
    public String getNomAlgorithme() {
        return "recherche sync stream 8";
    }

    @Override
    public HyperLien<LivreRessource> chercher(Livre l, List<HyperLien<BibliothequeArchive>> bibliotheques, Client client) {
        return bibliotheques.stream()
                .parallel()
                .map(bibliotheque -> LienVersRessource.proxy(client, bibliotheque, BibliothequeArchive.class))
                .map(bibliotheque -> rechercheSync(bibliotheque, l))
                .filter(Objects::nonNull)
                .findAny()
                .orElse(null);
    }
}
