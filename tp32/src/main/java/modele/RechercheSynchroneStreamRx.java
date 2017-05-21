package modele;

import infrastructure.jaxrs.HyperLien;
import infrastructure.jaxrs.LienVersRessource;
import rx.Observable;
import rx.schedulers.Schedulers;

import javax.ws.rs.client.Client;
import java.util.List;
import java.util.Objects;

/**
 * Created by damien on 20/05/2017.
 */
public class RechercheSynchroneStreamRx extends RechercheSynchroneAbstraite {
    @Override
    public String getNomAlgorithme() {
        return "recherche sync stream rx";
    }

    @Override
    public HyperLien<LivreRessource> chercher(Livre l, List<HyperLien<BibliothequeArchive>> bibliotheques, Client client) {
        return Observable.from(bibliotheques)
                .map(bibliotheque -> LienVersRessource.proxy(client, bibliotheque, BibliothequeArchive.class))
                .flatMap(bibliotheque -> Observable.fromCallable(() -> rechercheSync(bibliotheque, l)))
                .subscribeOn(Schedulers.io())
                .filter(Objects::nonNull)
                .firstOrDefault(null)
                .toBlocking()
                .single();
    }
}
