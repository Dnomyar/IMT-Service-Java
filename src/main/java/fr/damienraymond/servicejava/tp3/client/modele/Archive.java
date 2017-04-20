package modele;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

public interface Archive {
	public LivreRessource sousRessource(IdentifiantLivre id) ; // Une sous-ressource ne peut-être une cible de requête.
		
}
