package fr.damienraymond.servicejava.serverStateless.rest.jaxrs;

import fr.damienraymond.servicejava.serverStateless.rest.ImplemSession;
import fr.damienraymond.servicejava.serverStateless.rest.Session;

public class ConversionStringSession {
	public static Session fromString(String x) {
		return new ImplemSession(x);
	}
}
