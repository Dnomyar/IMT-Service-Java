package fr.damienraymond.servicejava.serverStatefull.rest;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import fr.damienraymond.servicejava.serverStatefull.rest.jaxb.TraductionSession;

@XmlJavaTypeAdapter(TraductionSession.class)
@XmlRootElement(name="session")
public interface Session {
	public int getNumero();
}
