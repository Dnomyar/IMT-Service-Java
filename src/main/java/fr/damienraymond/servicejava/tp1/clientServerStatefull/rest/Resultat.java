package fr.damienraymond.servicejava.tp1.clientServerStatefull.rest;

import fr.damienraymond.servicejava.tp1.clientServerStatefull.rest.jaxb.TraductionResultat;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlJavaTypeAdapter(TraductionResultat.class)
@XmlRootElement(name = "resultat")
public interface Resultat {
    boolean isValide();

    Session getId();
}
