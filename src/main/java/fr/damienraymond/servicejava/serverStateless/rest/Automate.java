package fr.damienraymond.servicejava.serverStateless.rest;

public interface Automate {

    Session initier();

    Resultat accepter(char x, Session id);

}

