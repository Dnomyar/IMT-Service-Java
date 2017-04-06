package fr.damienraymond.servicejava.serverStateless.rest;

import fr.damienraymond.servicejava.serverStateless.rest.jaxb.TraductionSession;
import fr.damienraymond.servicejava.serverStateless.rest.jaxrs.ConversionStringSession;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlJavaTypeAdapter(TraductionSession.class)
@XmlRootElement(name = "session")
public interface Session {

    static Session fromString(String x) {
        return ConversionStringSession.fromString(x);
    }

    int getNumero();

    Etat getEtatExecution();
}
