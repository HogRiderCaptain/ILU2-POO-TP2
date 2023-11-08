package controleur;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import personnages.Chef;
import personnages.Gaulois;
import villagegaulois.Village;

import java.util.Arrays;

class ControlAfficherVillageTest {
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
	void testControlAfficherVillage(){
		ControlAfficherMarche controlAfficherMarche = new ControlAfficherMarche(village);
		assertNotNull(controlAfficherMarche, "Constructeur ne renvoie pas null");
	}	
	
	@Test
	void testDonnerNomsVillageois(){
		ControlAfficherVillage controlAfficherVillage = new ControlAfficherVillage(village);
		assertTrue(Arrays.equals(controlAfficherVillage.donnerNomsVillageois(), new String[] {"Abraracourcix"}));
		
		Gaulois bonemine = new Gaulois("Bonemine", 10);
		village.ajouterHabitant(bonemine);
		village.installerVendeur(bonemine,"fleurs", 15);
		controlAfficherVillage = new ControlAfficherVillage(village);
		assertTrue(Arrays.equals(controlAfficherVillage.donnerNomsVillageois(), new String[] {"Abraracourcix","Bonemine"}));

	}
	
	@Test
	void testDonnerNomVillage(){
		ControlAfficherVillage controlAfficherVillage = new ControlAfficherVillage(village);
		assertTrue(controlAfficherVillage.donnerNomVillage().equals("le village des irréductibles"));
		Village village2 = new Village("le village des Gourmands", 10, 5);
		abraracourcix = new Chef("Abraracourcix", 10, village2);
		village2.setChef(abraracourcix);
		ControlAfficherVillage controlAfficherVillage2 = new ControlAfficherVillage(village2);
		assertTrue(controlAfficherVillage2.donnerNomVillage().equals("le village des Gourmands"));
	}	
	
	@Test
	void testDonnerNbEtals(){
		ControlAfficherVillage controlAfficherVillage = new ControlAfficherVillage(village);
		assertTrue(controlAfficherVillage.donnerNbEtals() == 5);
		
		Village village2 = new Village("le village des Gourmands", 20, 10);
		abraracourcix = new Chef("Abraracourcix", 10, village2);
		village2.setChef(abraracourcix);
		ControlAfficherVillage controlAfficherVillage2 = new ControlAfficherVillage(village2);
		assertTrue(controlAfficherVillage2.donnerNbEtals() == 10);
	}	
}
