package fr.damienraymond.servicejava.tp1.serverStatefull.rest;


import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/*

pure -> not update state
idempotent -> re-playable

GET : pure & idempotent
POST : not pure & not idempotent
PUT  : not pure & idempotent




- initier(ar) & Session(n) -> ar(n, OK) & Session(n + 1) & Execution(n, UN)
- accepter('a', n, ar) & Execution(n, UN) -> ar(n, OK) & Execution(n, DEUX)
- accepter('b', n, ar) & Execution(n, DEUX) -> ar(n, OK) & Execution(n, UN)
- accepter(x, n, ar) & Execution(n, e) & ((x, e) != ('a', UN) & (x, e) != ('b', DEUX))
  -> ar(n, KO) & Execution(n, e)
- accepter(x, n, ar) & (Execution(n, _) inactive)-> ar(n, KO)
- clore(n) & Execution(n, e) ->


 */

public interface Automate {


    @Path("state/init")
    @POST
    Session initier();

    @Path("state/accept/{state}")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    Resultat accepter(@PathParam("state") char x, Session id);

    @Path("close")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    void clore(Session id);


}

