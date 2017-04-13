package fr.damienraymond.servicejava.tp1.clientServerStatefull.rest;

import fr.damienraymond.servicejava.tp1.clientServerStatefull.rest.jaxb.TraductionSession;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlJavaTypeAdapter(TraductionSession.class)
@XmlRootElement(name = "session")
public interface Session {
    int getNumero();
}
