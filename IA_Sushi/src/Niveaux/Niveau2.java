package Niveaux;

import java.io.IOException;

import Ecran.Bouton;
import Ecran.CheminBouton;
import Ecran.RobotRepere;
import Exception.JeuFiniException;
import Ingredient.Ingredient;
import Sushi.Recette;
import Sushi.Sushi;

public class Niveau2 extends Niveau1{
	
	/********************************************/
		/* Attributs de la classe NIVEAU2 */
	/********************************************/
	
	// Bouton du t�l�phone pour le saumon
	protected Bouton t_salmon ;
	
	// Chemin pour restocker l'ingr�dient salmon
	protected CheminBouton<Bouton> stock_salmon ;
	
	// Ingr�dient ajout� au niveau 2
	protected Ingredient salmon ;
	
	// Recette du nouveau sushi du niveau 2
	protected Recette rec_Salmon;
	
	// Nouveau sushi du niveau 2
	protected Sushi Salmon_Roll ;

	
	/********************************************/
		/* Constructeur de la classe NIVEAU2 */
	/********************************************/
	
	public Niveau2 (RobotRepere r) throws IOException{
		super (r);
		 t_salmon = new Bouton(500, 330, r);
		 stock_salmon = new CheminBouton<Bouton>(
				t_topping, t_salmon, t_free, t_free);
		 salmon = new Ingredient("salmon", 35, 440, r, 5, 5, stock_salmon,
				524, 315, 6);
		 rec_Salmon = new Recette(rice, salmon, nori, salmon);
		 Salmon_Roll = new Sushi("Salmon_Roll", rec_Salmon, tapis_prep);
		 super.liste_sushi.add(Salmon_Roll);
		 super.Liste_Ingredients.add(salmon);
		 
	}
	
	/** 
	 * Méthode qui passse au niveau suivant
	 * @return variable de type Niveau3 qui contient les nouveaux sushis + ingrédients
	 * @throws IOException
	 */
	public Niveau3 nextLevel () throws IOException, JeuFiniException{
		System.out.println("Niveau3");
		return new Niveau3 (super.r);
	}
}
