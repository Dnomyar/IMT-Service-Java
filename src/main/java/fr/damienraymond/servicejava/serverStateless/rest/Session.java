package fr.damienraymond.servicejava.serverStateless.rest;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import fr.damienraymond.servicejava.serverStateless.rest.jaxb.TraductionSession;
import fr.damienraymond.servicejava.serverStateless.rest.jaxrs.ConversionStringSession;

@XmlJavaTypeAdapter(TraductionSession.class)
@XmlRootElement(name="session")
public interface Session {
	
	public static Session fromString(String x){
		return ConversionStringSession.fromString(x);
	}
	
	public int getNumero();
	public Etat getEtatExecution();
}
