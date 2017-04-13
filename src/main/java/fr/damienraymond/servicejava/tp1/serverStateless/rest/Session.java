package fr.damienraymond.servicejava.tp1.serverStateless.rest;

import fr.damienraymond.servicejava.tp1.serverStateless.rest.jaxb.TraductionSession;
import fr.damienraymond.servicejava.tp1.serverStateless.rest.jaxrs.ConversionStringSession;

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
