package infrastructure.jaxrs;

import infrastructure.jaxrs.annotations.Reponses404Null;
import infrastructure.jaxrs.annotations.ReponsesNull404GET;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientResponseContext;
import javax.ws.rs.client.ClientResponseFilter;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.ext.ReaderInterceptor;
import javax.ws.rs.ext.ReaderInterceptorContext;
import java.io.IOException;

/**
 * Created by damien on 16/05/2017.
 */
@Provider
@Reponses404Null
@Priority(Priorities.HEADER_DECORATOR)
public class AdapterReponses404Null implements ClientResponseFilter, ReaderInterceptor {

    final String HEADER_NAME = "Pragma";
    final String ERREUR_404 = "ERREUR404";

    public void filter(ClientRequestContext clientRequestContext, ClientResponseContext clientResponseContext) throws IOException {
        if (clientResponseContext.getStatus() == Response.Status.NOT_FOUND.getStatusCode()){
            clientResponseContext.getHeaders().add(HEADER_NAME, ERREUR_404);
        }
    }

    public Object aroundReadFrom(ReaderInterceptorContext readerInterceptorContext) throws IOException, WebApplicationException {
        final MultivaluedMap<String, String> headers = readerInterceptorContext.getHeaders();

        final String pragma = headers.getFirst(HEADER_NAME);
        if (ERREUR_404.equals(pragma)){
            return null;
        }
        return readerInterceptorContext.proceed();
    }
}
