package Niveaux;

import java.io.IOException;

import Ecran.Bouton;
import Ecran.CheminBouton;
import Ecran.RobotRepere;
import Exception.JeuFiniException;
import Ingredient.Ingredient;
import Sushi.Recette;
import Sushi.Sushi;

public class Niveau3 extends Niveau2 {
	
	/********************************************/
		/* Attributs de la classe NIVEAU3 */
	/********************************************/
	
	// Bouton pour commander l'ingr�dient shrimp
	protected Bouton t_shrimp;
	
	// Chemin de boutons pour commander l'ingr�dient shrimp
	protected CheminBouton<Bouton> stock_shrimp;
	
	// Ingr�dient ajout� au niveau 3
	protected Ingredient shrimp;
	
	// Recette du niveau sushi du niveau 3
	protected Recette rec_Shrimp;
	
	// Nouveau sushi du niveau 3
	protected Sushi Shrimp_Sushi;

	
	/********************************************/
		/* Constructeur de la classe NIVEAU3 */
	/********************************************/
	public Niveau3 (RobotRepere r) throws IOException {
		super(r);
		t_shrimp = new Bouton(500, 220, r);
		stock_shrimp = new CheminBouton<Bouton>(t_topping, t_shrimp, t_free, t_free);
		shrimp = new Ingredient("shrimp", 35, 330, r, 5, 5, stock_shrimp, 524, 205, 6);
		rec_Shrimp = new Recette(rice, shrimp, nori, shrimp);
		Shrimp_Sushi = new Sushi("Shrimp_Sushi", rec_Shrimp, tapis_prep);
		super.liste_sushi.add(Shrimp_Sushi);
		super.Liste_Ingredients.add(shrimp);
	}

	
	/********************************************/
		/* M�thodes de la classe NIVEAU3 */
	/********************************************/
	/** 
	 * Méthode qui passse au niveau suivant
	 * @return variable de type Niveau4 qui contient les nouveaux sushis + ingrédients
	 * @throws IOException
	 */
	public Niveau4 nextLevel () throws IOException, JeuFiniException{
		System.out.println("Niveau4");
		return new Niveau4 (super.r);
	}
}
