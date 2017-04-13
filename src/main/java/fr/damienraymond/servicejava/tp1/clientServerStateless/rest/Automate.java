package fr.damienraymond.servicejava.tp1.clientServerStateless.rest;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

public interface Automate {

    @Path("state/init")
    @GET // pure & idempotent
    Session initier();

    @Path("state/accept/{lettre}")
    @GET // pure & idempotent
    Resultat accepter(@PathParam("lettre") char x, @QueryParam("id") Session id);
}

