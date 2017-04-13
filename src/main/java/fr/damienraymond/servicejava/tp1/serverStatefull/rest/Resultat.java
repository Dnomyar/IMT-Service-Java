package fr.damienraymond.servicejava.tp1.serverStatefull.rest;

import fr.damienraymond.servicejava.tp1.serverStatefull.rest.jaxb.TraductionResultat;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlJavaTypeAdapter(TraductionResultat.class)
@XmlRootElement(name = "resultat")
public interface Resultat {
    boolean isValide();

    Session getId();
}
