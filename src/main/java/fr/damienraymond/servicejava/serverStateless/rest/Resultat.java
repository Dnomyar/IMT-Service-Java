package fr.damienraymond.servicejava.serverStateless.rest;

import fr.damienraymond.servicejava.serverStateless.rest.jaxb.TraductionResultat;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlJavaTypeAdapter(TraductionResultat.class)
@XmlRootElement(name = "resultat")
public interface Resultat {
    boolean isValide();

    Session getId();
}
