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
	/* M�thodes de la classe CheminBouton */
	/********************************************/
	
	
	/** 
	 * Méthode pour itérer sur une liste de boutons
	 */
	public Iterator <Bouton> iterator() {
		return liste.iterator();
	}
	
	/**
	 * Méthode qui ajoute un bouton � la liste de bouton
	 * @param index auquel le bouton doit �tre ajout� dans la liste
	 * @param bouton qu'il faut ajouter dans la liste
	 */
	public void add (int index, Bouton b){
		this.liste.add(index, b);
	}
	
	/**
	 * M�thode qui renvoie le bouton � l'index indiqu� en param�teres
	 * @param index
	 * @return le bouton � l'index indiqu�
	 */
	public Bouton get (int index){
		return this.liste.get(index);
	}
	
	/**
	 * M�thode qui retourne la longueur du chemin de bouton
	 * @return int 
	 */
	public int length (){
		return this.liste.size();
	}
}
