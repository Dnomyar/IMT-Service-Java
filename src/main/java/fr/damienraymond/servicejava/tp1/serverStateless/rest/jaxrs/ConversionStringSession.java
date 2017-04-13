package fr.damienraymond.servicejava.tp1.serverStateless.rest.jaxrs;

import fr.damienraymond.servicejava.tp1.serverStateless.rest.ImplemSession;
import fr.damienraymond.servicejava.tp1.serverStateless.rest.Session;

public class ConversionStringSession {
    public static Session fromString(String x) {
        return new ImplemSession(x);
    }
}
