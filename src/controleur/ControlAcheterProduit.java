package controleur;

import personnages.Gaulois;
import villagegaulois.Village;

public class ControlAcheterProduit {
	private Village village;
	private ControlTrouverEtalVendeur controlTrouverEtalVendeur;
	private ControlVerifierIdentite controlVerifierIdentite;

	public ControlAcheterProduit(ControlVerifierIdentite controlVerifierIdentite,
			ControlTrouverEtalVendeur controlTrouverEtalVendeur,
			Village village) {
		this.village = village;
		this.controlVerifierIdentite = controlVerifierIdentite;
		this.controlTrouverEtalVendeur = controlTrouverEtalVendeur;
	}

	public String acheterProduit(String produit) {
		StringBuilder chaine = new StringBuilder();
		Gaulois[] listeVendeur = village.rechercherVendeursProduit(produit);
		for (int i=0;i<listeVendeur.length;i++) {
			chaine.append((i+1) + " - " + listeVendeur[i].getNom() +"\n");
		}
		return chaine.toString();
	}
}
