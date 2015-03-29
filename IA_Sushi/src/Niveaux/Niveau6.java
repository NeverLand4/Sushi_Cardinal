package Niveaux;

import java.io.IOException;

import Ecran.RobotRepere;
import Exception.JeuFiniException;
import Sushi.Recette;
import Sushi.Sushi;

public class Niveau6 extends Niveau5 {
	
	/********************************************/
		/* Attributs de la classe NIVEAU6 */
	/********************************************/
	
	// Recette du nouveau sushi
	protected Recette rec_Combo = new Recette (rice, nori, roe, salmon, shrimp, unagi, rice);
	// Nouveau sushi
	protected Sushi Combo_Sushi = new Sushi("Combo_Sushi", rec_Combo, tapis_prep);

	
	/********************************************/
		/* M�thodes de la classe NIVEAU6 */
	/********************************************/
	public Niveau6 (RobotRepere r) throws IOException{
		super (r);
		 rec_Combo = new Recette (rice, nori, roe, salmon, shrimp, unagi, rice);
		 Combo_Sushi = new Sushi("Combo_Sushi", rec_Combo, tapis_prep);
		 super.liste_sushi.add(Combo_Sushi);
	}
	
	/********************************************/
		/* Constructeurs de la classe NIVEAU6 */
	/********************************************/
	/** 
	 * Méthode qui passse au niveau suivant
	 * @return variable de type Niveau6 qui contient les nouveaux sushis + ingrédients
	 * @throws IOException
	 */
	public Niveau7 nextLevel() throws JeuFiniException, IOException{
		System.out.println("Niveau7");
		return new Niveau7 (super.r);
	}
}
