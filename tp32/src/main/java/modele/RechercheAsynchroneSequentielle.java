package modele;

import infrastructure.jaxrs.HyperLien;
import infrastructure.jaxrs.Outils;

import javax.ws.rs.client.Client;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

/**
 * Created by damien on 20/05/2017.
 */
public class RechercheAsynchroneSequentielle extends RechercheAsynchroneAbstraite {
    @Override
    public String getNomAlgorithme() {
        return "recherche async seq";
    }

    @Override
    public HyperLien<LivreRessource> chercher(Livre l, List<HyperLien<BibliothequeArchive>> bibliotheques, Client client) {

        List<Future<HyperLien<LivreRessource>>> futures = new ArrayList<>();

        HyperLien<LivreRessource> res = null;

        for (HyperLien<BibliothequeArchive> bibliotheque: bibliotheques) {
            futures.add(rechercheAsync(bibliotheque, l, client));
        }


        for (Future<HyperLien<LivreRessource>> future: futures) {
            if (res == null){
                res = Outils.remplirPromesse(future);
            }
        }

        return res;
    }
}
