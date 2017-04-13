package fr.damienraymond.servicejava.tp1.clientServerStateless.rest.jaxrs;

import fr.damienraymond.servicejava.tp1.clientServerStateless.rest.ImplemSession;
import fr.damienraymond.servicejava.tp1.clientServerStateless.rest.Session;

public class ConversionStringSession {
    public static Session fromString(String x) {
        return new ImplemSession(x);
    }
}
