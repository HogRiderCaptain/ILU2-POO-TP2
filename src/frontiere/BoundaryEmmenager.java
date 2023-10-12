package frontiere;

import controleur.ControlEmmenager;

public class BoundaryEmmenager {
	private ControlEmmenager controlEmmenager;

	public BoundaryEmmenager(ControlEmmenager controlEmmenager) {
		this.controlEmmenager = controlEmmenager;
	}

	public void emmenager(String nomVisiteur) {
		if (controlEmmenager.isHabitant(nomVisiteur)) {
			System.out.println("Mais vous êtes déjà un habitant du village !");
		} else {
			StringBuilder question = new StringBuilder();
			question.append("Êtes-vous :\n");
			question.append("1 - un druide.\n");
			question.append("2 - un gaulois.\n");
			int choixUtilisateur = -1;
			do {
				choixUtilisateur = Clavier.entrerEntier(question.toString());
				switch (choixUtilisateur) {
				case 1:
					emmenagerDruide(nomVisiteur);
					break;

				case 2:

					break;

				default:
					System.out.println("Vous devez choisir le chiffre 1 ou 2 !");
					break;
				}
			} while (choixUtilisateur != 1 && choixUtilisateur != 2);
		}
	}

	private void emmenagerDruide(String nomVisiteur) {
		StringBuilder chaine = new StringBuilder(), chaine2 = new StringBuilder(), chaine3 = new StringBuilder();
		chaine.append("Bienvenue druide" + nomVisiteur);
		System.out.println(chaine.toString());
		int forceDruide = Clavier.entrerEntier("Quelle est votre force ?"), effetPotionMax, effetPotionMin;
		chaine2.append("Quelle est la force de la potion la plus faible que vous produisez ?");
		chaine3.append("Quelle est la force de la potion la plus forte que vous produisez ?");
		System.out.println(chaine2.toString());
		effetPotionMin = Clavier.entrerEntier("PotionMin");
		System.out.println(chaine3.toString());
		effetPotionMax = Clavier.entrerEntier("PotionMax");
		do {
			System.out.println("Attention druide, vous vous êtes trompé entre le minimum et le maximum");
			System.out.println(chaine2.toString());
			effetPotionMin = Clavier.entrerEntier("PotionMin");
			System.out.println(chaine3.toString());
			effetPotionMax = Clavier.entrerEntier("PotionMax");
		} while (effetPotionMax < effetPotionMin);
		controlEmmenager.ajouterDruide(nomVisiteur, forceDruide, effetPotionMin, effetPotionMax);
	}
}
