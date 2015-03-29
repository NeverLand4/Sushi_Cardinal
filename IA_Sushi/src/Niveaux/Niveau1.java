package Niveaux;

import java.io.IOException;

import Ecran.*;
import Exception.JeuFiniException;
import Ingredient.*;
import Sushi.*;

public class Niveau1 {
	
	/********************************************/
		/* Attributs de la classe NIVEAU1 */
	/********************************************/
	
	// Coordonnées des boutons du téléphone et du tapis de préparation
	public static final int t_topping_x = 515;
	public static final int t_topping_y = 270;
	public static final int t_free_x = 520;
	public static final int t_free_y = 290;
	public static final int tapis_prep_x = 200;
	public static final int tapis_prep_y = 380;
	public static final int t_rice_x = 515;
	public static final int t_rice_y = 294;
	public static final int t_nori_x = 500;
	public static final int t_nori_y = 275;
	public static final int t_roe_x = 570;
	public static final int t_roe_y = 275;
	
	// On crée des boutons pour le téléphone et le tapis de préparation
	protected Bouton t_topping, t_free, tapis_prep, t_rice, t_nori, t_roe;
	
	// On crée des chemins de boutons qui correspondent aux chemins pour restocker les ingrédients
	protected CheminBouton<Bouton> stock_rice, stock_roe, stock_nori;
	
	// Les ingrédients utilisés dans le niveau 1
	protected Ingredient rice, nori, roe;
	
	// Les recettes des sushi du niveau 1
	protected Recette rec_onigri, rec_California, rec_Gunkan;
	
	// Les sushis du niveau 1
	protected Sushi Onigri, California_Roll, Gunkan_Maki;
	
	public RobotRepere r;
	
	// La liste des sushis du niveau 1
	protected Liste_Sushi liste_sushi;
	
	// La liste des ingrédients du niveau 1
	protected Liste_Ingredients Liste_Ingredients;
	
	
	/********************************************/
		/* Constructeur de la classe NIVEAU1 */
	/********************************************/

	public Niveau1(RobotRepere r) throws IOException {

		this.r = r;

		// Initialisation des boutons du tÃ©lÃ©phone
		t_topping = new Bouton (t_topping_x, t_topping_y, r);
		t_free = new Bouton(t_free_x, t_free_y, r);
		tapis_prep = new Bouton(tapis_prep_x, tapis_prep_y, r);
		t_rice = new Bouton(515, 294, r);
		t_nori = new Bouton(500, 275, r);
		t_roe = new Bouton(570, 275, r);

		// Initialisation des chemins pour commander les ingrÃ©dients
		stock_rice = new CheminBouton<Bouton>(t_rice, t_rice,t_free, t_free);
		stock_roe = new CheminBouton<Bouton>(t_topping, t_roe, t_free, t_free);
		stock_nori = new CheminBouton<Bouton>(t_topping, t_nori, t_free, t_free);

		// Initialisation des IngrÃ©dients
		rice = new Ingredient("rice", 90, 330, r, 10, 10, stock_rice, 524, 260, -9022635, 4);
		nori = new Ingredient("nori", 35, 380, r, 10, 10, stock_nori, 524, 260, 4);
		roe = new Ingredient("roe", 90, 380, r, 10, 10, stock_roe, 604, 260, 4 );

		// Initialisation de la liste des ingrÃ©dients
		Liste_Ingredients = new Liste_Ingredients(rice, nori, roe);

		// Initialisation des recettes
		rec_onigri = new Recette(rice, nori, rice);
		rec_California = new Recette(roe, nori, rice);
		rec_Gunkan = new Recette(roe, nori, roe, rice);

		// Initialisation des Sushi
		Onigri = new Sushi("Onigri", rec_onigri, tapis_prep);
		California_Roll = new Sushi("California_Roll", rec_California,
				tapis_prep);
		Gunkan_Maki = new Sushi("Gunkan_Maki", rec_Gunkan, tapis_prep);
		liste_sushi = new Liste_Sushi(Onigri, California_Roll, Gunkan_Maki);
	}

	public Liste_Sushi getListeSushi() {
		return liste_sushi;
	}
	public Liste_Ingredients getListeIngredients() {
		return Liste_Ingredients;
	}
	
	/** 
	 * MÃ©thode qui passse au niveau suivant
	 * @return variable de type Niveau2 qui contient les nouveaux sushis + ingrÃ©dients
	 * @throws IOException
	 */
	public Niveau2 nextLevel() throws IOException, JeuFiniException {
		System.out.println("Niveau2");
		return new Niveau2(this.r);
	}

}
