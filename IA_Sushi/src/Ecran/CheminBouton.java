package Ecran;

import java.util.Iterator;
import java.util.ArrayList;
public class CheminBouton <Bouton> implements Iterable <Bouton>{
	
	/********************************************/
	/* Attributs de la classe CheminBouton */
	/********************************************/
	
	// Liste qui contient les boutons 
	private ArrayList <Bouton> liste;
	
	
	
	/********************************************/
	/* Constructeurs de la classe Bouton */
	/********************************************/
	public CheminBouton (Bouton b1, Bouton b2, Bouton b3){
		liste = new ArrayList <Bouton> ();
		liste.add(b1);
		liste.add(b2);
		liste.add(b3);
	}
	
	public CheminBouton (Bouton b1, Bouton b2, Bouton b3, Bouton b4){
		liste = new ArrayList <Bouton> ();
		liste.add(b1);
		liste.add(b2);
		liste.add(b3);
		liste.add(b4);

	}

	public CheminBouton (Bouton b1, Bouton b2, Bouton b3, Bouton b4, Bouton b5){
		liste = new ArrayList <Bouton> ();
		liste.add(b1);
		liste.add(b2);
		liste.add(b3);
		liste.add(b4);
		liste.add(b5);

	}
	
	/********************************************/
	/* Méthodes de la classe CheminBouton */
	/********************************************/
	
	
	/** 
	 * MÃ©thode pour itÃ©rer sur une liste de boutons
	 */
	public Iterator <Bouton> iterator() {
		return liste.iterator();
	}
	
	/**
	 * MÃ©thode qui ajoute un bouton à  la liste de bouton
	 * @param index auquel le bouton doit être ajouté dans la liste
	 * @param bouton qu'il faut ajouter dans la liste
	 */
	public void add (int index, Bouton b){
		this.liste.add(index, b);
	}
	
	/**
	 * Méthode qui renvoie le bouton à l'index indiqué en paramèteres
	 * @param index
	 * @return le bouton à l'index indiqué
	 */
	public Bouton get (int index){
		return this.liste.get(index);
	}
	
	/**
	 * Méthode qui retourne la longueur du chemin de bouton
	 * @return int 
	 */
	public int length (){
		return this.liste.size();
	}
}
