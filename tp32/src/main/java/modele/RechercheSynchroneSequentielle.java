package modele;

import infrastructure.jaxrs.HyperLien;
import infrastructure.jaxrs.LienVersRessource;

import javax.ws.rs.client.Client;
import java.util.List;

/**
 * Created by damien on 20/05/2017.
 */
public class RechercheSynchroneSequentielle extends RechercheSynchroneAbstraite {
    @Override
    public String getNomAlgorithme() {
        return "recherche sync seq";
    }

    @Override
    public HyperLien<LivreRessource> chercher(Livre l, List<HyperLien<BibliothequeArchive>> bibliotheques, Client client) {


        HyperLien<LivreRessource> bibliothequeArchiveHyperLien = null;

        for(HyperLien<BibliothequeArchive> bibliotheque: bibliotheques) {
            if (bibliothequeArchiveHyperLien == null){
                final BibliothequeArchive proxy =
                        LienVersRessource.proxy(
                                client,
                                bibliotheque,
                                BibliothequeArchive.class);

                bibliothequeArchiveHyperLien = rechercheSync(proxy, l);
            }
        }

        return bibliothequeArchiveHyperLien;
    }
}
