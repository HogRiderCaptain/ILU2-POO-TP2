package controleur;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import personnages.Chef;
import personnages.Gaulois;
import villagegaulois.Village;

import java.util.Arrays;

class ControlAfficherMarcheTest {
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
	void testControlAfficherMarcheTest() {
		ControlAfficherMarche controlAfficherMarche = new ControlAfficherMarche(village);
		assertNotNull(controlAfficherMarche, "Constructeur ne renvoie pas null");
	}	
	
	@Test
	void testdonnerInfosMarche() {
		ControlAfficherMarche controlAfficherMarche = new ControlAfficherMarche(village);
		String[] controle = controlAfficherMarche.donnerInfosMarche();
		assertTrue(Arrays.equals(controle, new String[] {"Bonemine","15","fleurs"} ));
		Gaulois mauvaisemine = new Gaulois("Mauvaisemine", 10);
		
		village.ajouterHabitant(mauvaisemine);
		village.installerVendeur(mauvaisemine,"pot", 10);		
		controlAfficherMarche = new ControlAfficherMarche(village);
		controle = controlAfficherMarche.donnerInfosMarche();
		assertFalse(Arrays.equals(controle, new String[] {"Bonemine","15","fleurs"} ));
	}	
}
