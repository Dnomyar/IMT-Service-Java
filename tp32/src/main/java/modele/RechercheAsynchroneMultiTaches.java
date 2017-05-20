package modele;

import infrastructure.jaxrs.HyperLien;
import infrastructure.jaxrs.LienVersRessource;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.InvocationCallback;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * Created by damien on 20/05/2017.
 */
public class RechercheAsynchroneMultiTaches extends RechercheAsynchroneAbstraite {
    @Override
    public String getNomAlgorithme() {
        return "recherche async multi";
    }

    @Override
    public HyperLien<LivreRessource> chercher(Livre l, List<HyperLien<BibliothequeArchive>> bibliotheques, Client client) {

        final CountDownLatch countDownLatch = new CountDownLatch(bibliotheques.size());

        HyperLien<LivreRessource> res = new HyperLien<>();

        for (HyperLien<BibliothequeArchive> bibliotheque: bibliotheques) {

            LienVersRessource.proxy(client, bibliotheque, BibliothequeArchive.class);

            rechercheAsyncAvecRappel(bibliotheque, l, client, new InvocationCallback<HyperLien<LivreRessource>>(){
                @Override
                public void completed(HyperLien<LivreRessource> livreRessourceHyperLien) {

                    if (livreRessourceHyperLien == null) {
                        countDownLatch.countDown();
                    } else {

                        res.setUri(livreRessourceHyperLien.getUri());

                        while (countDownLatch.getCount() > 0){
                            countDownLatch.countDown();
                        }

                    }

                }

                @Override
                public void failed(Throwable throwable) {
                    System.out.println("Error occurred : " + throwable.getMessage());
                    throwable.printStackTrace();
                }
            });
        }


        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return res;
    }
}
