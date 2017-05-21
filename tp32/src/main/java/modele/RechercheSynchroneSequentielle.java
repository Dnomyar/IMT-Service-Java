package modele;

import infrastructure.jaxrs.HyperLien;
import infrastructure.jaxrs.LienVersRessource;

import javax.ws.rs.client.Client;
import java.util.List;
import java.util.Objects;

import static java.util.Objects.isNull;

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


        HyperLien<LivreRessource> ressourceHyperLien;
        for (HyperLien<BibliothequeArchive> bibliotheque : bibliotheques) {
            ressourceHyperLien = this.rechercheSync(
                    LienVersRessource.proxy(client, bibliotheque, BibliothequeArchive.class),
                    l);
            if(!isNull(ressourceHyperLien)) return ressourceHyperLien;
        }
        return null;

//        HyperLien<LivreRessource> bibliothequeArchiveHyperLien = null;
//
//        for(HyperLien<BibliothequeArchive> bibliotheque: bibliotheques) {
//            final BibliothequeArchive proxy =
//                    LienVersRessource.proxy(
//                            client,
//                            bibliotheque,
//                            BibliothequeArchive.class);
//
//            final HyperLien<LivreRessource> ressourceHyperLien = rechercheSync(proxy, l);
//
//            if(! isNull(ressourceHyperLien)){
//                return ressourceHyperLien;
//            }
//        }
//
//        return null;
    }
}
