package controleur;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import personnages.Chef;
import personnages.Gaulois;
import villagegaulois.Village;
import villagegaulois.Etal;
import controleur.ControlTrouverEtalVendeur;

class ControlTrouverEtalVendeurTest {
	private Village village;
	private Chef abraracourcix;

	@BeforeEach
	public void initialiserSituation() {
		System.out.println("Initialisation...");
		village = new Village("le village des irréductibles", 10, 5);
		abraracourcix = new Chef("Abraracourcix", 10, village);
		village.setChef(abraracourcix);
	}

	@Test
	void testControlTrouverEtal() {
		ControlTrouverEtalVendeur controlTrouverEtalVendeur = new ControlTrouverEtalVendeur(village);
		assertNotNull(controlTrouverEtalVendeur, "Constructeur ne renvoie pas null");
	}
	
	@Test
	void testTrouverEtalVendeur() {
		
		Gaulois bonemine = new Gaulois("Bonemine",10);
		village.ajouterHabitant(bonemine);
		ControlTrouverEtalVendeur controlTrouverEtalVendeur = new ControlTrouverEtalVendeur(village);
		Etal etalTrouve = controlTrouverEtalVendeur.trouverEtalVendeur("Bonemine");
		assertTrue(etalTrouve == null);
		village.installerVendeur(bonemine, "fleurs", 10);	
		controlTrouverEtalVendeur = new ControlTrouverEtalVendeur(village);
		etalTrouve = controlTrouverEtalVendeur.trouverEtalVendeur("Bonemine");
		assertTrue(etalTrouve.contientProduit("fleurs") && etalTrouve.getVendeur() == bonemine && etalTrouve.getQuantite() == 10);
		
		village.installerVendeur(bonemine, "pots", 5);	
		controlTrouverEtalVendeur = new ControlTrouverEtalVendeur(village);
		etalTrouve = controlTrouverEtalVendeur.trouverEtalVendeur("Bonemine");
		assertTrue((etalTrouve.contientProduit("fleurs") && etalTrouve.getVendeur() == bonemine && etalTrouve.getQuantite() == 10)
				 || (etalTrouve.contientProduit("pots") && etalTrouve.getVendeur() == bonemine && etalTrouve.getQuantite() == 5));
		
		etalTrouve = controlTrouverEtalVendeur.trouverEtalVendeur("Mauvaisemine");
		assertTrue(etalTrouve == null);
	}
}