package Sushi;

import java.io.IOException;
import java.awt.Rectangle;
import java.awt.Robot;

import Client.Client;
import Client.EnsClients;
import Ecran.Bouton;
import Ecran.Image;
import Ecran.Repere;
import Ingredient.Ingredient;
import JEU.SushiGoRound;
import Niveaux.Niveau1;
public class Sushi {
	
	/********************************************/
		/* Attribus de la classe SUSHI */
	/********************************************/
	
	// Nom du sushi
	public String nom ;
	
	// Image du sushi
	private Image im_sushi;
	
	// Image du tapis de pr�paration vide, permet de v�rifier que la commande a bien �t� 
	// envoy�e et qu'elle n'est pas bloqu�e sur le tapis
	private Image tapis;
	
	// Recette du sushi
	private Recette recette;
	
	// Le bouton valider correspond au tapis de pr�paration sur lequel il faut cliquer pour 
	// envoyer le sushi
	private Bouton valider;
	
	
	/********************************************/
		/* Constructeur de la classe SUSHI */
	/********************************************/
	public Sushi (String nom,  Recette recette, Bouton valider) throws IOException{
		this.nom = nom;
		this.recette = recette;
		this.valider = valider;
		this.im_sushi = Image.readIm(nom+".png");
		this.tapis = Image.readIm("tapis1.png");
		}
	
	
	/********************************************/
		/* M�thodes de la classe SUSHI */
	/********************************************/
	/**
	 * Méthode qui fait un sushi
	 * @param Robot
	 * @param Client
	 * @param Ensemble de clients
	 * @param presquefin est un boole�n qui est vrai si le niveau est presque fini
	 * @return vrai si le sushi a été fait, faux sinon
	 * @throws IOException
	 */
	public boolean fait (Robot r, Repere rep, Client c, EnsClients e, boolean presquefin, Niveau1 n) throws IOException{
		boolean stock = false;
		System.out.println("Fait le sushi");
		// Si le sushi est reconnu
		if (this.reconnait(c)){
		// On vérifie que le stock est suffisant pour chaque ingrédient, si ce n'est pas le cas, on restocke, 
		// et on renvoie faux pour laisser le temps aux ingrédients d'arriver	
		if (!Ingredient.restock(this.recette, presquefin, r, e)) return false;
		if (!this.reconnait(c)) return false;
		// On fait le sushi
		for (Ingredient i : this.recette){
			i.clicGauche();	
		}
		
		this.valider.clicGauche();
		r.mouseMove(rep.getCoord_x(), rep.getCoord_y());
		r.delay(1660);
		Image tapis_occ = new Image (r.createScreenCapture(new Rectangle (rep.getCoord_x()+166, rep.getCoord_y()+346, 100, 100)));
		while (tapis_occ.equals(this.tapis)==false && !SushiGoRound.finNiveau(r, rep)){
			tapis_occ = new Image (r.createScreenCapture(new Rectangle (rep.getCoord_x()+166, rep.getCoord_y()+346, 100, 100)));
			System.out.println("Pas la meme image");
			this.valider.clicGauche();
			e.debarasseA();
		}
		// Après avoir fait le sushi, on revérifie le stock et on restocke si nécessaire
		if (!presquefin){
		boolean peu = false;
		for (Ingredient i :  n.getListeIngredients()){
			if (i.getStock() < 2) return true;
			if ( i.getStock()==4 || i.getStock()==3 || i.getStock()==2){
				peu = true;
			}
		}
		
		for (Ingredient i : n.getListeIngredients()){
			if (i.getStock() < i.getSeuil()){
				System.out.println("Stock Insuffisant");
				if (!i.restock()) return true;
				stock = true;
			
		}
		}
		// Si on a restocké au moins 1 ingrédient, on clique sur les assiettes de tous les clients
		if (peu) r.delay(2500);
		if (stock) {
			e.debarasseA();
		}
		}
		return true;
		}
		return false;
	}
	

	
	/**
	 * Si la commande du client correspond au sushi this on renvoie vrai
	 * @param Client 
	 * @return vrai si la commande correspond au sushi this, faux sinon
	 */
	public boolean reconnait (Client c){
		if (this.im_sushi.equals(c.imClient())) return true;
		return false;
	}
	
}