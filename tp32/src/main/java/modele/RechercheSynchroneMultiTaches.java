package modele;

import infrastructure.jaxrs.HyperLien;
import infrastructure.jaxrs.LienVersRessource;

import javax.ws.rs.client.Client;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by damien on 20/05/2017.
 */
public class RechercheSynchroneMultiTaches extends RechercheSynchroneAbstraite {
    @Override
    public String getNomAlgorithme() {
        return "recherche sync multi";
    }

    @Override
    public HyperLien<LivreRessource> chercher(Livre l, List<HyperLien<BibliothequeArchive>> bibliotheques, Client client) {

        final CountDownLatch countDownLatch = new CountDownLatch(bibliotheques.size());

        final ExecutorService executorService = Executors.newCachedThreadPool();

        HyperLien<LivreRessource> result = new HyperLien<>();

        for(HyperLien<BibliothequeArchive> bibliotheque: bibliotheques) {

            executorService.submit(() -> {
                final BibliothequeArchive proxy =
                        LienVersRessource.proxy(client, bibliotheque, BibliothequeArchive.class);

                final HyperLien<LivreRessource> livreRessourceHyperLien =
                        rechercheSync(proxy, l);


                if (null == livreRessourceHyperLien) {
                    // Décrémentation pour chaque itération ou on ne trouve pas le livre
                    countDownLatch.countDown();
                } else {
                     result.setUri(livreRessourceHyperLien.getUri());

                     while(countDownLatch.getCount() > 0) {
                        countDownLatch.countDown();
                     }
                }

            });
        }


        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return result;
    }
}
