package controleur;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import personnages.Chef;
import personnages.Gaulois;
import villagegaulois.Village;
import controleur.ControlTrouverEtalVendeur;
import controleur.ControlVerifierIdentite;
import controleur.ControlAcheterProduit;

import java.util.Arrays;

class ControlAcheterProduitTest {
	private Village village;
	private Chef abraracourcix;
	
	@BeforeEach
	public void initialiserSituation() {
		System.out.println("Initialisation...");
		village = new Village("le village des irréductibles", 10, 5);
		abraracourcix = new Chef("Abraracourcix", 10, village);
		village.setChef(abraracourcix);
		Gaulois bonemine = new Gaulois("Bonemine", 10);
		village.ajouterHabitant(bonemine);
		village.installerVendeur(bonemine,"fleurs", 15);
	}

	@Test
	void testControlAcheterProduit() {
		ControlTrouverEtalVendeur controlTrouverEtalVendeur = new ControlTrouverEtalVendeur(village);
		ControlVerifierIdentite controlVerifierIdentite = new ControlVerifierIdentite(village);
		ControlAcheterProduit controlAcheterProduit = new ControlAcheterProduit(controlVerifierIdentite, controlTrouverEtalVendeur, village);
		assertNotNull(controlAcheterProduit, "Constructeur ne renvoie pas null");
	}
	
	@Test
	void testListeVendeurs(){

		ControlTrouverEtalVendeur controlTrouverEtalVendeur = new ControlTrouverEtalVendeur(village);
		ControlVerifierIdentite controlVerifierIdentite = new ControlVerifierIdentite(village);
		ControlAcheterProduit controlAcheterProduit = new ControlAcheterProduit(controlVerifierIdentite, controlTrouverEtalVendeur, village);
		String[] listeVendeurs = {"Bonemine"};
		assertFalse(Arrays.equals(controlAcheterProduit.listeVendeurs("caillou"),listeVendeurs));
		assertTrue(Arrays.equals(controlAcheterProduit.listeVendeurs("fleurs"),listeVendeurs));
	}

	@Test
	void testConfirmationAchat(){
		
		ControlTrouverEtalVendeur controlTrouverEtalVendeur = new ControlTrouverEtalVendeur(village);
		ControlVerifierIdentite controlVerifierIdentite = new ControlVerifierIdentite(village);
		ControlAcheterProduit controlAcheterProduit = new ControlAcheterProduit(controlVerifierIdentite, controlTrouverEtalVendeur, village);
		assert(controlAcheterProduit.quantiteEtal("Bonemine") == 15);
		int acheter = controlAcheterProduit.confirmationAchat(5, "Bonemine");
		assert(acheter == 5);
		assert(controlAcheterProduit.quantiteEtal("Bonemine") == 10);
		acheter = controlAcheterProduit.confirmationAchat(11, "Bonemine");
		assert(acheter == 10);
		assert(controlAcheterProduit.quantiteEtal("Bonemine") == 0);
		acheter = controlAcheterProduit.confirmationAchat(1, "Bonemine");
		assert(acheter == 0);
	}

}
