package fr.damienraymond.servicejava.clientServerStatefull.rest;

public interface Automate {

    Session initier();

    Resultat accepter(char x, Session id);

    void clore(Session id);
}

