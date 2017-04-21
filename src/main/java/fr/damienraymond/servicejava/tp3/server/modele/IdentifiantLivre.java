package fr.damienraymond.servicejava.tp3.server.modele;

public interface IdentifiantLivre {

    String getId();

    static IdentifiantLivre fromString(String str){
        return new ImplemIdentifiantLivre(str);
    }
}
