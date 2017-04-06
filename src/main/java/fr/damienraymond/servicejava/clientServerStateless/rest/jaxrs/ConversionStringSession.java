package fr.damienraymond.servicejava.clientServerStateless.rest.jaxrs;

import fr.damienraymond.servicejava.clientServerStateless.rest.ImplemSession;
import fr.damienraymond.servicejava.clientServerStateless.rest.Session;

public class ConversionStringSession {
	public static Session fromString(String x) {
		return new ImplemSession(x);
	}
}
