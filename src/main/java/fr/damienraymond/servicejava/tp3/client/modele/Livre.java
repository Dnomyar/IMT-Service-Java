package fr.damienraymond.servicejava.tp3.client.modele;

import fr.damienraymond.servicejava.tp3.client.configuration.JAXRS;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "livre")
public interface Livre {
    @GET
    @Path(JAXRS.SOUSCHEMIN_TITRE)
    String getTitre();
}
