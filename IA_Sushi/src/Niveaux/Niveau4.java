package Niveaux;

import java.io.IOException;

import Ecran.Bouton;
import Ecran.CheminBouton;
import Ecran.RobotRepere;
import Exception.JeuFiniException;
import Ingredient.Ingredient;
import Sushi.Recette;
import Sushi.Sushi;

public class Niveau4 extends Niveau3{
	
	/********************************************/
		/* Attributs de la classe NIVEAU4 */
	/********************************************/
	
	// Bouton de l'ingr�dient unagi dans le t�l�phone
	protected Bouton t_unagi ;
	
	// Chemin de boutons pour restocker l'ingr�dient unagi
	protected CheminBouton<Bouton> stock_unagi;
	
	// Ingr�dient unagi ajout� au niveau 4
	protected Ingredient unagi;
	
	// Recette du nouveau sushi
	protected Recette rec_Unagi ;
	
	// Nouveau sushi
	protected Sushi Unagi_Roll;

	/********************************************/
		/* Constructeur de la classe NIVEAU4 */
	/********************************************/
	public Niveau4 (RobotRepere r) throws IOException{
		super (r);
		 t_unagi = new Bouton(548, 207, r);
		 stock_unagi = new CheminBouton<Bouton>(
				t_topping, t_unagi, t_free, t_free);
		 unagi = new Ingredient("unagi", 90, 440, r, 5, 5, stock_unagi, 600,
				210, 6);
		 rec_Unagi = new Recette(rice, unagi, nori, unagi);
		 Unagi_Roll = new Sushi("Unagi_Roll", rec_Unagi, tapis_prep);
		 super.liste_sushi.add(Unagi_Roll);
		 super.Liste_Ingredients.add(unagi);
		
	}
	
	/********************************************/
		/* M�thode de la classe NIVEAU4 */
	/********************************************/
	
	/** 
	 * Méthode qui passse au niveau suivant
	 * @return variable de type Niveau5 qui contient les nouveaux sushis + ingrédients
	 * @throws IOException
	 */
	public Niveau5 nextLevel () throws IOException, JeuFiniException{
		System.out.println("Niveau5");
		return new Niveau5(super.r);
	}
}
