package fr.damienraymond.servicejava.serverStatefull.rest;

public interface Automate {

    Session initier();

    Resultat accepter(char x, Session id);

    void clore(Session id);
}

