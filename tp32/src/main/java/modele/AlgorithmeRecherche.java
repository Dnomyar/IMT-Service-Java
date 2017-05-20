package modele;

import java.util.List;

import javax.ws.rs.client.Client;

import infrastructure.jaxrs.HyperLien;

public interface AlgorithmeRecherche extends AlgorithmeNomme{
	HyperLien<LivreRessource> chercher(Livre l, List<HyperLien<BibliothequeArchive>> bibliotheques, Client client);
	default AlgorithmeNomme nom(){
		return new ImplemAlgorithmeNomme(this.getNomAlgorithme());
	}
}
