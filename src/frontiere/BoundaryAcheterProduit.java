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
		String message;
		String listeVendeur = controlAcheterProduit.acheterProduit(nomProduit);
		if (listeVendeur == null) {
			System.out.println("Désolé, personne ne vend ce produit au marché.");
		} else {
			System.out.println("Chez quel commercant voulez-vous acheter des " + nomProduit + " ?");
			System.out.println(listeVendeur);
		}
}
