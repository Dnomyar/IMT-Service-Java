package fr.damienraymond.servicejava.tp3.server.modele;

import fr.damienraymond.servicejava.tp3.server.configuration.JAXRS;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "livre")
public interface Livre {

    @GET
    @Path(JAXRS.SOUSCHEMIN_TITRE)
    String getTitre();


    static Livre fromString(String str){
        return new ImplemLivre(str);
    }

}
