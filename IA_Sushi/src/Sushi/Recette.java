package Sushi;

import java.util.ArrayList;
import java.util.Iterator;

import Ingredient.Ingredient;

@SuppressWarnings("hiding")
public class Recette implements Iterable <Ingredient> {

	/********************************************/
		/* Attribus de la classe RECETTE */
	/********************************************/
	
	// Liste d'ingrédients
	private ArrayList<Ingredient> liste;

	
	/********************************************/
		/* Constructeurs de la classe RECETTE */
	/********************************************/
	public Recette(Ingredient i1, Ingredient i2, Ingredient i3) {
		this.liste = new ArrayList<Ingredient>();
		this.liste.add(i1);
		this.liste.add(i2);
		this.liste.add(i3);
	}

	public Recette(Ingredient i1, Ingredient i2, Ingredient i3, Ingredient i4) {
		this.liste = new ArrayList<Ingredient>();
		this.liste.add(i1);
		this.liste.add(i2);
		this.liste.add(i3);
		this.liste.add(i4);

	}
	


	public Recette(Ingredient i1, Ingredient i2, Ingredient i3, Ingredient i4,
			Ingredient i5) {
		this.liste = new ArrayList<Ingredient>();
		this.liste.add(i1);
		this.liste.add(i2);
		this.liste.add(i3);
		this.liste.add(i4);
		this.liste.add(i5);
	}
	
	public Recette(Ingredient i1, Ingredient i2, Ingredient i3, Ingredient i4,
			Ingredient i5, Ingredient i6) {
		this.liste = new ArrayList<Ingredient>();
		this.liste.add(i1);
		this.liste.add(i2);
		this.liste.add(i3);
		this.liste.add(i4);
		this.liste.add(i5);
		this.liste.add(i6);
		}
	
	public Recette(Ingredient i1, Ingredient i2, Ingredient i3, Ingredient i4,
			Ingredient i5, Ingredient i6, Ingredient i7) {
		this.liste = new ArrayList<Ingredient>();
		this.liste.add(i1);
		this.liste.add(i2);
		this.liste.add(i3);
		this.liste.add(i4);
		this.liste.add(i5);
		this.liste.add(i6);
		this.liste.add(i7);

		}
	
	
	/********************************************/
		/* Méthodes de la classe RECETTE */
	/********************************************/
	
	/**
	 * Méthode qui renvoie la taille de la liste de sushi
	 * @return int
	 */
	public int size (){
		return this.liste.size();
	}
	
	/**
	 * Méthode qui permet d'itérer sur un objet Recette
	 */
	public Iterator<Ingredient> iterator() {
        return liste.iterator();
	}

}
