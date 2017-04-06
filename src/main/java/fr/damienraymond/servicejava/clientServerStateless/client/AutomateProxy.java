package fr.damienraymond.servicejava.clientServerStateless.client;

import fr.damienraymond.servicejava.clientServerStateless.rest.Automate;
import fr.damienraymond.servicejava.clientServerStateless.rest.Resultat;
import fr.damienraymond.servicejava.clientServerStateless.rest.Session;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

public class AutomateProxy implements Automate {
    private WebTarget cibleInitier;
    private WebTarget cibleAccepter;
    private MediaType typeContenu;

    public AutomateProxy(String uriBase, MediaType typeContenu) {
        WebTarget cible = AppliCliente.clientJAXRS().target(uriBase);
        cibleInitier = cible.path("etat/initial");
        cibleAccepter = cible.path("etat/suivant");
        this.typeContenu = typeContenu;
    }

    @Override
    public Session initier() {
        return null; // TODO
    }

    @Override
    public Resultat accepter(char x, Session id) {
        return null; // TODO
    }

}
