package fr.damienraymond.servicejava.tp3.server.infrastructure.jaxrs;

import fr.damienraymond.servicejava.tp3.server.infrastructure.jaxrs.annotations.ReponsesCreatedPOST;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
@ReponsesCreatedPOST
@Priority(Priorities.HEADER_DECORATOR)
public class AdapterReponsesCreatedPOST implements ContainerResponseFilter {
    public AdapterReponsesCreatedPOST() {
        System.out.println("** Initialisation du filtre de type "
                + this.getClass());
    }

    @Override
    public void filter(ContainerRequestContext requete,
                       ContainerResponseContext reponse) throws IOException {
        // Cas POST
        if (requete.getMethod().equalsIgnoreCase("POST")) {
            if (reponse.getStatus() == Response.Status.OK.getStatusCode()) {
                Object entite = reponse.getEntity();
                if (entite instanceof HyperLien<?>) {
                    System.out.println("201 CREATED !");
                    reponse.setStatus(Response.Status.CREATED.getStatusCode());
                    HyperLien<?> lien = (HyperLien<?>) entite;
                    MultivaluedMap<String, Object> enTetes = reponse.getHeaders();
                    enTetes.putSingle("Location", lien.getUri());
                    reponse.setEntity(null, null, null);
                }
            }
        }
    }

}
