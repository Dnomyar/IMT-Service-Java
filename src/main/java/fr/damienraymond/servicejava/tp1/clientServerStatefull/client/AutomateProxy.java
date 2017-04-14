package fr.damienraymond.servicejava.tp1.clientServerStatefull.client;

import fr.damienraymond.servicejava.tp1.clientServerStatefull.rest.Automate;
import fr.damienraymond.servicejava.tp1.clientServerStatefull.rest.ImplemResultat;
import fr.damienraymond.servicejava.tp1.clientServerStatefull.rest.Resultat;
import fr.damienraymond.servicejava.tp1.clientServerStatefull.rest.Session;

import javax.inject.Singleton;
import javax.ws.rs.Path;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;


@Path("/")
@Singleton
public class AutomateProxy implements Automate {
    private WebTarget cibleInitier;
    private WebTarget cibleAccepter;
    private WebTarget cibleClore;
    private MediaType typeContenu;

    public AutomateProxy(String uriBase, MediaType typeContenu) {
        WebTarget cible = AppliCliente.clientJAXRS().target(uriBase);
        cibleInitier = cible.path("state/init");
        cibleAccepter = cible.path("state/accept");
        cibleClore = cible.path("close");
        this.typeContenu = typeContenu;
    }

    @Override
    public Session initier() {
        return cibleInitier
                .request(typeContenu).post(null).readEntity(Session.class);

    }

    @Override
    public Resultat accepter(char x, Session id) {
        final ImplemResultat implemResultat = new ImplemResultat();
        implemResultat.setId(id);
        implemResultat.setValide(true);
        return cibleAccepter
                .path(Character.toString(x))
                .queryParam("id", id)
                .request(typeContenu)
                .post(
                        Entity.entity(
                                implemResultat,
                                MediaType.APPLICATION_JSON_TYPE
                        ),
                        Resultat.class
                );
    }

    @Override
    public void clore(Session id) {
        cibleInitier.request(typeContenu).put(null);
    }

}
