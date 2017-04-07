package fr.damienraymond.servicejava.clientServerStatefull.rest;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/*
pure -> Update state
idempotent -> re-playable

GET : pure & idempotent
POST : not pure & not idempotent
PUT  : pure & idempotent
 */

public interface Automate {

    @Path("state/init")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    Session initier();

    @Path("state/accept/{letter}")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    Resultat accepter(@PathParam("letter") char x, Session id);

    @Path("close")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    void clore(Session id);
}

