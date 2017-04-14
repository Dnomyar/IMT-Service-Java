package fr.damienraymond.servicejava.tp2.clientRegister.infrastructure.jaxrs;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.ws.rs.Priorities;
import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
@Priority(Priorities.HEADER_DECORATOR)
public class AjouterPrecondition implements ClientRequestFilter {


    private Cache cache;

    @Inject
    public AjouterPrecondition(Cache c) {
        cache = c;
        System.out.println("Filtre initialisé " + this + " : " + this.getClass());
        System.out.println("- Cache partagé : " + this.cache);
    }

    /*
	 * Filtre / requête.
	 */
    @Override
    public synchronized void filter(ClientRequestContext requete)
            throws IOException {

        // Premier cas : requête PUT
        if (handlePutRequest(requete)) {
            return;
        }
        // Second cas : requête GET
        handleGetRequest(requete);
    }

    private void handleGetRequest(ClientRequestContext requete) {
        if (requete.getMethod().equalsIgnoreCase("GET")) {
            // Si la version est définie dans le cache, l'affecter au champ
            // HttpHeaders.IF_NONE_MATCH
            // de l'en-tête de la requête.
            // Cf. ClientRequestContext.getHeaders().
            if (cache.version != null) {
                MultivaluedMap<String, Object> enTetes = requete.getHeaders();
                enTetes.putSingle(HttpHeaders.IF_NONE_MATCH,
                        cache.version);
            }
            return;
        }
    }

    private boolean handlePutRequest(ClientRequestContext requete) {
        if (requete.getMethod().equalsIgnoreCase("PUT")) {
            // Si la version est définie dans le cache, l'affecter au champ
            // HttpHeaders.IF_MATCH
            // de l'en-tête de la requête.
            // Cf. ClientRequestContext.getHeaders().
            if (cache.version != null) {
                MultivaluedMap<String, Object> enTetes = requete.getHeaders();
                enTetes.putSingle(HttpHeaders.IF_MATCH,
                        cache.version);
            }
            return true;
        }
        return false;
    }

}
