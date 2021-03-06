package fr.damienraymond.servicejava.tp1.serverStateless.rest;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/*

pure -> not update state
idempotent -> re-playable

GET : pure & idempotent
POST : not pure & not idempotent
PUT  : not pure & idempotent


- initier(ar) & Session(n) -> ar(n, UN) & Session(n + 1)
- accepter('a', n, UN, ar) > ar(n, DEUX)
- accepter('b', n, DEUX, ar) -> ar(n, UN)
- accepter(x, n, e, ar) & ((x, e) != ('a', UN) & (x, e) != ('b', DEUX)) -> ar(n, KO)

 */
public interface Automate {


    @Path("state/init")
    @POST // not pure & not idempotent
    Session initier();

    @Path("state/accept/{lettre}")
    @GET // pure & idempotent
    @Produces(MediaType.APPLICATION_JSON)
    Resultat accepter(@PathParam("lettre") char x, @QueryParam("id") Session id);

}

