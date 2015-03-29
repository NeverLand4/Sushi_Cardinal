package Ingredient;

import java.util.ArrayList;
import java.util.Iterator;

public class Liste_Ingredients implements Iterable<Ingredient> {
	

	/********************************************/
		/* Attributs de la classe LISTE_INGREDIENT */
	/********************************************/
	
	// Liste d'ingr�dients
	private ArrayList<Ingredient> liste;

	
	/********************************************/
		/* Constructeurs de la classe LISTE_INGREDIENT */
	/********************************************/
	
	public Liste_Ingredients (Ingredient i1, Ingredient i2, Ingredient i3, Ingredient i4, Ingredient i5, Ingredient i6){
		liste = new ArrayList <Ingredient> ();
		liste.add(i1);
		liste.add(i2);
		liste.add(i3);
		liste.add(i4);
		liste.add(i5);
		liste.add(i6);

	}
	public Liste_Ingredients (Ingredient i1, Ingredient i2, Ingredient i3){
		liste = new ArrayList <Ingredient> ();
		liste.add(i1);
		liste.add(i2);
		liste.add(i3);

	}
	
	/********************************************/
		/* M�thodes de la classe LISTE_INGREDIENT */
	/********************************************/
	
	/** 
	 * M�thode qui ajoute un ingr�dient � la liste
	 * @param Ingredient � ajouter
	 */
	public void add (Ingredient i){
		this.liste.add(i);
	}
	
	/**
	 * Permet d'it�rer sur un objet Liste_Ingredient
	 */
	public Iterator<Ingredient> iterator() {
		return liste.iterator();
	}
	
	/**
	 * Renvoie l'ingr�dient � la position i dans la liste
	 * @param int
	 * @return Ingredient
	 */
	public Ingredient get (Ingredient i){
		return this.liste.get(this.liste.indexOf(i));
	}
	
}
