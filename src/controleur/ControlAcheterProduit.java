package controleur;

import personnages.Gaulois;
import villagegaulois.Village;
import villagegaulois.Etal;

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

	public String[] listeVendeurs(String produit) {
		Gaulois[] listeVendeur = village.rechercherVendeursProduit(produit);
		if(listeVendeur == null){
			return null;
		} 
		String[] nomVendeurs = new String[listeVendeur.length];
		for (int i=0;i<listeVendeur.length;i++) {
			nomVendeurs[i] = listeVendeur[i].getNom();
		}
		return nomVendeurs;
	}

	public int confirmationAchat(int nbreAchat,String nomGaulois){
		Etal etal = controlTrouverEtalVendeur.trouverEtalVendeur(nomGaulois);
		int quantite = etal.getQuantite();
		int quantiteAcheter = etal.acheterProduit(nbreAchat);
		return quantite;
	}
}
