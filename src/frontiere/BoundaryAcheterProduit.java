package frontiere;

import java.util.Scanner;
import controleur.ControlAcheterProduit;

public class BoundaryAcheterProduit {
	private Scanner scan = new Scanner(System.in);
	private ControlAcheterProduit controlAcheterProduit;

	public BoundaryAcheterProduit(ControlAcheterProduit controlAcheterProduit) {
		this.controlAcheterProduit = controlAcheterProduit;
	}

	public void acheterProduit(String nomAcheteur) {
		System.out.println("Quel produit voulez-vous acheter ?");
		String nomProduit = scan.nextLine();
		String[] nomVendeurs = controlAcheterProduit.listeVendeurs(nomProduit);
		if (nomVendeurs == null) {
			System.out.println("Désolé, personne ne vend ce produit au marché.");
		} else {
			System.out.println("Chez quel commercant voulez-vous acheter des " + nomProduit + " ?");
			for(int i=0;i<nomVendeurs.length;i++){
				System.out.println((i+1) + " - " + nomVendeurs[i]);
			}
			int numCommercant = scan.nextInt();
			String nomGaulois = nomVendeurs[numCommercant-1];
			System.out.println(nomAcheteur + " se déplace jusqu'à l'étal du vendeur " + nomGaulois 
			+ "\nBonjour " + nomAcheteur + ",\nCombien de " + nomProduit + " voulez-vous acheter ?");
			int nbreAchat = scan.nextInt();

			int quantiteAcheter = controlAcheterProduit.confirmationAchat(nbreAchat, nomGaulois);
			int quantiteEtal = controlAcheterProduit.quantiteEtal(nomGaulois);
			StringBuilder message = new StringBuilder(nomAcheteur); 
			if (quantiteEtal >= nbreAchat) {
				message.append(" achète " + nbreAchat +" "+nomProduit+" à "+nomGaulois+".");
			} else {
				message.append(" veut acheter "+nbreAchat+" "+nomProduit+", malheureusement ");
				if(quantiteEtal < nbreAchat) {
					message.append(nomGaulois+" n'en a plus que "+quantiteEtal+". "+nomAcheteur+" achète tout le stock de "+nomGaulois+".");	
				} else {
					message.append("il n'y en a plus !");
				}
			System.out.println(message.toString());
			}
		}	
	}
}
