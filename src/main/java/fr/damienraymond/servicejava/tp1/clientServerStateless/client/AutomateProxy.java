package fr.damienraymond.servicejava.tp1.clientServerStateless.client;

import fr.damienraymond.servicejava.tp1.clientServerStateless.rest.Automate;
import fr.damienraymond.servicejava.tp1.clientServerStateless.rest.Resultat;
import fr.damienraymond.servicejava.tp1.clientServerStateless.rest.Session;

import javax.inject.Singleton;
import javax.ws.rs.Path;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

public class AutomateProxy implements Automate {
    private WebTarget cibleInitier;
    private WebTarget cibleAccepter;
    private MediaType typeContenu;

    public AutomateProxy(String uriBase, MediaType typeContenu) {
        WebTarget cible = AppliCliente.clientJAXRS().target(uriBase);
        cibleInitier = cible.path("state/init");
        cibleAccepter = cible.path("state/accept");
        this.typeContenu = typeContenu;
    }

    @Override
    public Session initier() {
        return cibleInitier.request(typeContenu).post(null).readEntity(Session.class);
    }

    @Override
    public Resultat accepter(char x, Session id) {
        return cibleAccepter
                .path(Character.toString(x))
                .queryParam("id", id)
                .request(typeContenu).get().readEntity(Resultat.class);
    }

}
