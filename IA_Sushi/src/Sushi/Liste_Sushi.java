package Sushi;

import java.util.ArrayList;
import java.util.Iterator;

public class Liste_Sushi implements Iterable<Sushi> {
	
	/********************************************/
		/* Attributs de la classe LISTE_SUSHI */
	/********************************************/
	
	// Liste de sushis
	private ArrayList<Sushi> liste;
	
	/********************************************/
		/* Constructeurs de la classe LISTE_SUSHI */
	/********************************************/
	public Liste_Sushi (Sushi i1, Sushi i2, Sushi i3){
		liste = new ArrayList <Sushi> ();
		liste.add(i1);
		liste.add(i2);
		liste.add(i3);
	}
	
	public Liste_Sushi (Sushi i1, Sushi i2, Sushi i3, Sushi i4, Sushi i5, Sushi i6, Sushi i7, Sushi i8){
		liste = new ArrayList <Sushi> ();
		liste.add(i1);
		liste.add(i2);
		liste.add(i3);
		liste.add(i4);
		liste.add(i5);
		liste.add(i6);
		liste.add(i7);
		liste.add(i8);
	}
	
	/********************************************/
		/* Méthodes de la classe LISTE_SUSHI */
	/********************************************/
	public void add (Sushi s){
		liste.add(s);
	}
	
	public Iterator<Sushi> iterator() {
		return liste.iterator();
	}
}
