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
public class Sushi {
	
	/********************************************/
		/* Attribus de la classe SUSHI */
	/********************************************/
	
	// Nom du sushi
	public String nom ;
	
	// Image du sushi
	private Image im_sushi;
	
	// Image du tapis de préparation vide, permet de vérifier que la commande a bien été 
	// envoyée et qu'elle n'est pas bloquée sur le tapis
	private Image tapis;
	
	// Recette du sushi
	private Recette recette;
	
	// Le bouton valider correspond au tapis de préparation sur lequel il faut cliquer pour 
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
		/* Méthodes de la classe SUSHI */
	/********************************************/
	/**
	 * MÃ©thode qui fait un sushi
	 * @param Robot
	 * @param Client
	 * @param Ensemble de clients
	 * @param presquefin est un booleén qui est vrai si le niveau est presque fini
	 * @return vrai si le sushi a Ã©tÃ© fait, faux sinon
	 * @throws IOException
	 */
	public boolean fait (Robot r, Repere rep, Client c, EnsClients e, boolean presquefin) throws IOException{
		boolean stock = false;
		System.out.println("Fait le sushi");
		// Si le sushi est reconnu
		if (this.reconnait(c)){
		// On vÃ©rifie que le stock est suffisant pour chaque ingrÃ©dient, si ce n'est pas le cas, on restocke, 
		// et on renvoie faux pour laisser le temps aux ingrÃ©dients d'arriver	
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
			e.debarasse();
		}
		// AprÃ¨s avoir fait le sushi, on revÃ©rifie le stock et on restocke si nÃ©cessaire
		if (!presquefin){
		for (Ingredient i : this.recette){
			if (i.getStock() < i.getSeuil()){
				System.out.println("Stock Insuffisant");
				if (!i.restock()) return false;
				stock = true;
			
		}
		}
		// Si on a restockÃ© au moins 1 ingrÃ©dient, on clique sur les assiettes de tous les clients
		if (stock) {
			e.debarasse();
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