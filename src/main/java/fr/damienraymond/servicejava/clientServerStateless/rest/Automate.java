package fr.damienraymond.servicejava.clientServerStateless.rest;

public interface Automate {
	
	Session initier();

	Resultat accepter(char x, Session id); 

}

