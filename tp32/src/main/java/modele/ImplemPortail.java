package modele;

import configuration.Initialisation;
import configuration.JAXRS;
import configuration.Orchestrateur;
import infrastructure.jaxrs.HyperLien;
import infrastructure.jaxrs.HyperLiens;
import infrastructure.jaxrs.LienVersRessource;
import infrastructure.jaxrs.Outils;

import javax.inject.Singleton;
import javax.ws.rs.Path;
import javax.ws.rs.client.Client;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.core.GenericType;
import java.lang.reflect.Method;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.Future;

/**
 * Created by damien on 16/05/2017.
 */
@Singleton
@Path(JAXRS.CHEMIN_PORTAIL)
public class ImplemPortail implements Portail, RechercheAsynchroneDeclenchantRechercheSynchrone {


    private ConcurrentMap<AlgorithmeNomme, AlgorithmeRecherche> algos;

    private Client client;

    private AlgorithmeRecherche algo;

    private List<HyperLien<BibliothequeArchive>> biblio;


    private static GenericType<HyperLien<LivreRessource>>  typeRetourChercherAsync;


    static {
        Method m = null;
        try {
            m = ImplemPortail.class.getDeclaredMethod("chercher", Livre.class);
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        typeRetourChercherAsync = new GenericType<>(m.getGenericReturnType());
    }


    public ImplemPortail() {
        algos = new ConcurrentHashMap<>();
        client = Orchestrateur.clientJAXRS();
        algo = null;
        biblio = Initialisation.bibliotheques();

        addAlgo(new RechercheSynchroneSequentielle());
        addAlgo(new RechercheSynchroneMultiTaches());
        addAlgo(new RechercheSynchroneStreamParallele());
        addAlgo(new RechercheSynchroneStreamRx());
        addAlgo(new RechercheAsynchroneSequentielle());
        addAlgo(new RechercheAsynchroneMultiTaches());
        addAlgo(new RechercheAsynchroneStreamParallele());
        addAlgo(new RechercheAsynchroneStreamRx());
    }

    private void addAlgo(AlgorithmeRecherche algo){
        algos.put(algo.nom(), algo);
    }

    public static GenericType<HyperLien<LivreRessource>> typeRetourChercherAsync(){
        return typeRetourChercherAsync;
    }

    public void algorithmeRecherche(AlgorithmeNomme algo) {
        System.out.println("ImplemPortail.algorithmeRecherche(" + algo + ")");
        this.algo = algos.getOrDefault(algo, this.algo);
    }

    public HyperLien<LivreRessource> chercher(Livre l) {
        return algo.chercher(l, biblio, client);
    }


    public HyperLiens<LivreRessource> repertorier() {
        System.out.println("ImplemPortail.repertorier");
        return biblio.stream()
                .map(e -> LienVersRessource.proxy(client, e, BibliothequeArchive.class))
                .map(Bibliotheque::repertorier)
                .reduce(new HyperLiens<>(), Outils::sommeHyperLiens);
    }
}
