package fr.damienraymond.servicejava.clientServerStatefull.rest.jaxb;

import fr.damienraymond.servicejava.clientServerStatefull.rest.ImplemSession;
import fr.damienraymond.servicejava.clientServerStatefull.rest.Session;

import javax.ws.rs.ext.Provider;

@Provider
public class TraductionSession extends javax.xml.bind.annotation.adapters.XmlAdapter<ImplemSession, Session> {

    public TraductionSession() {
        System.out.println("Chargement de l'adaptateur JAXB de type : " + this.getClass());
    }

    @Override
    public ImplemSession marshal(Session s0) throws Exception {
        ImplemSession s1 = new ImplemSession();
        s1.setNumero(s0.getNumero());
        return s1;
    }

    @Override
    public Session unmarshal(ImplemSession s) throws Exception {
        return s;
    }

}
