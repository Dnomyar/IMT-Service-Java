package fr.damienraymond.servicejava.tp3.client.infrastructure.jaxrs;

import fr.damienraymond.servicejava.tp3.client.client.AppliCliente;
import org.glassfish.jersey.client.proxy.WebResourceFactory;

import javax.ws.rs.client.WebTarget;

public class ClientRessource<T> {

    private T proxyRessource;

    public ClientRessource(HyperLien<T> k, Class<T> typeInterface) {
        WebTarget cible = AppliCliente.clientJAXRS().target(k.getUri());
        proxyRessource = WebResourceFactory.newResource(typeInterface, cible);
    }

    public static <T> T proxy(HyperLien<T> lien, Class<T> type) {
        return (new ClientRessource<T>(lien, type)).getProxyRessource();
    }

    public T getProxyRessource() {
        return proxyRessource;
    }
}
