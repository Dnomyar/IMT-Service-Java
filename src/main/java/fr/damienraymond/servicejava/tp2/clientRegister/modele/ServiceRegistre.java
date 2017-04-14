package fr.damienraymond.servicejava.tp2.clientRegister.modele;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("optimist")
public interface ServiceRegistre {

    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    Ressource set(Ressource n);

    @GET
    @Produces(MediaType.APPLICATION_XML)
    Ressource get();
}
