package fr.damienraymond.servicejava.tp1.clientServerStateless.rest;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

public interface Automate {

    @Path("state/init")
    @POST // not pure & not idempotent
    Session initier();

    @Path("state/accept/{lettre}")
    @GET // pure & idempotent
    @Produces(MediaType.APPLICATION_JSON)
    Resultat accepter(@PathParam("lettre") char x, @QueryParam("id") Session id);
}

