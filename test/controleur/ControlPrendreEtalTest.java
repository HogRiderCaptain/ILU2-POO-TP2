package controleur;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import personnages.Chef;
import personnages.Gaulois;
import villagegaulois.Village;
import controleur.ControlPrendreEtal;
import controleur.ControlVerifierIdentite;

class ControlPrendreEtalTest {
	private Village village;
	private Chef abraracourcix;

	@BeforeEach
	public void initialiserSituation() {
		System.out.println("Initialisation...");
		village = new Village("le village des irréductibles", 10, 2);
		abraracourcix = new Chef("Abraracourcix", 10, village);
		village.setChef(abraracourcix);
	}

	@Test
	void testControlPrendreEtal() {
		ControlVerifierIdentite controlVerifierIdentite = new ControlVerifierIdentite(village);
		ControlPrendreEtal controlPrendreEtal = new ControlPrendreEtal(controlVerifierIdentite,village);
		assertNotNull(controlPrendreEtal, "Constructeur ne renvoie pas null");
	}
	
	@Test
	void testResteEtals() {
		Gaulois bonemine = new Gaulois("Bonemine",10);
		village.ajouterHabitant(bonemine);
		village.installerVendeur(bonemine, "fleurs", 15);
		ControlVerifierIdentite controlVerifierIdentite = new ControlVerifierIdentite(village);
		ControlPrendreEtal controlPrendreEtal = new ControlPrendreEtal(controlVerifierIdentite,village);
		assertTrue(controlPrendreEtal.resteEtals());
		
		Village village2 = new Village("le village des Gourmands", 10, 1);
		abraracourcix = new Chef("Abraracourcix", 10, village2);
		village2.setChef(abraracourcix);
		village2.installerVendeur(bonemine, "fleurs", 15);
		controlVerifierIdentite = new ControlVerifierIdentite(village2);
		controlPrendreEtal = new ControlPrendreEtal(controlVerifierIdentite,village2);
		assertFalse(controlPrendreEtal.resteEtals());
	}

	@Test
	void testPrendreEtal() {
		Gaulois bonemine = new Gaulois("Bonemine",10);
		Gaulois mauvaisemine = new Gaulois("Mauvaisemine",10);
		Gaulois moyennemine = new Gaulois("Moyennemine",10);
		village.ajouterHabitant(bonemine);
		village.ajouterHabitant(mauvaisemine);
		village.ajouterHabitant(moyennemine);
		ControlVerifierIdentite controlVerifierIdentite = new ControlVerifierIdentite(village);
		ControlPrendreEtal controlPrendreEtal = new ControlPrendreEtal(controlVerifierIdentite,village);
		int indice = controlPrendreEtal.prendreEtal("Bonemine","fleurs",15);
		assertTrue(indice == 0);
		indice = controlPrendreEtal.prendreEtal("Mauvaisemine","pots",5);
		assertTrue(indice == 1);
		

		controlVerifierIdentite = new ControlVerifierIdentite(village);
		controlPrendreEtal = new ControlPrendreEtal(controlVerifierIdentite,village);
		indice = controlPrendreEtal.prendreEtal("Moyennemine","terre",2);
		assertTrue(indice == -1);
		
		village.partirVendeur(mauvaisemine);
		controlVerifierIdentite = new ControlVerifierIdentite(village);
		controlPrendreEtal = new ControlPrendreEtal(controlVerifierIdentite,village);
		indice = controlPrendreEtal.prendreEtal("Moyennemine","terre",2);
		assertTrue(indice == 1);		
	}
	
	@Test
	void testVerifierIdentite() {
		Gaulois bonemine = new Gaulois("Bonemine",10);
		ControlVerifierIdentite controlVerifierIdentite = new ControlVerifierIdentite(village);
		ControlPrendreEtal controlPrendreEtal = new ControlPrendreEtal(controlVerifierIdentite,village);
		Boolean bool = controlPrendreEtal.verifierIdentite("Bonemine");
		assertFalse(bool);
		village.ajouterHabitant(bonemine);
		controlVerifierIdentite = new ControlVerifierIdentite(village);
		controlPrendreEtal = new ControlPrendreEtal(controlVerifierIdentite,village);
		bool = controlPrendreEtal.verifierIdentite("Bonemine");
		assertTrue(bool);
	}
}