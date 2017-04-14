package fr.damienraymond.servicejava.tp2.serverRegister.modele;

import fr.damienraymond.servicejava.tp2.serverRegister.infrastructure.jaxrs.annotations.*;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

public interface ServiceRegistre {

    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    @StatRequetes
    @StatReponses
    @AtomiciteRequeteReponseServeur
    @CacheClient
    @EcritureOptimiste
    @VersionReponses
    Ressource set(Ressource n);

    @GET
    @Produces(MediaType.APPLICATION_XML)
    @StatRequetes
    @StatReponses
    @AtomiciteRequeteReponseServeur
    @CacheClient
    @EcritureOptimiste
    @VersionReponses
    Ressource get();
}



