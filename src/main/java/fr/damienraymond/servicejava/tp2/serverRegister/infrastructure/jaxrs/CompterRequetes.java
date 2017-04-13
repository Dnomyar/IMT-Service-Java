package fr.damienraymond.servicejava.tp2.serverRegister.infrastructure.jaxrs;

import fr.damienraymond.servicejava.tp2.serverRegister.infrastructure.jaxrs.annotations.StatRequetes;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

@Provider
@StatRequetes
@Priority(Priorities.AUTHORIZATION)
public class CompterRequetes implements ContainerRequestFilter {

    private AtomicInteger in = new AtomicInteger(0);

    private AtomicInteger reqGet = new AtomicInteger(0);
    private AtomicInteger reqPut = new AtomicInteger(0);

    public CompterRequetes() {
        System.out.println("* Initialisation du filtre " + this + " : " + this.getClass());
    }

    @Override
    public void filter(ContainerRequestContext requete) throws IOException {
        in.incrementAndGet();
        if (requete.getMethod().equalsIgnoreCase("PUT")) {
            reqPut.incrementAndGet();
        }
        if (requete.getMethod().equalsIgnoreCase("GET")) {
            reqGet.incrementAndGet();
        }
        System.out.println(this + " - Requêtes - total : " + in.get()
                + " dont PUT : " + reqPut.get()
                + " , GET : " + reqGet.get());
    }

}
