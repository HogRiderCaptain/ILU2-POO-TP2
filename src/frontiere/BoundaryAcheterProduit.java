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

			phaseAchat(nbreAchat,nomGaulois,nomAcheteur,nomProduit);

		}	
	}

	public void phaseAchat(int nbreAchat,String nomGaulois, String nomAcheteur, String nomProduit){
		int quantiteEtal = controlAcheterProduit.confirmationAchat(nbreAchat, nomGaulois);
		StringBuilder message = new StringBuilder(nomAcheteur); 
		if (quantiteEtal >= nbreAchat) {
			message.append(" achète ");
			message.append(nbreAchat);
			message.append(" ");
			message.append(nomProduit);
			message.append(" à ");
			message.append(nomGaulois);
		} else 
			message.append(" veut acheter ");
			message.append(nbreAchat);
			message.append(" ");
			message.append(nomProduit);
			if(quantiteEtal < nbreAchat) {
			message.append(", malheuresement ");
			message.append(nomGaulois);
			message.append(" n'en a plus que ");
			message.append(quantiteEtal);
			message.append(". ");
			message.append(nomAcheteur);
			message.append(" achère tout le stock de ");
			message.append(nomGaulois);			
			} else {
			message.append(", malheureusementil n'y en a plus !");
		}
		message.append(".");
		System.out.println(message.toString());
	}
}
