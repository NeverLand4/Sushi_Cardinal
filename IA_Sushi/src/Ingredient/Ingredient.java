package Ingredient;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import Client.EnsClients;
import Ecran.Bouton;
import Ecran.CheminBouton;
import Ecran.Repere;
import Ecran.RobotRepere;
import Sushi.Recette;

public class Ingredient extends Bouton {

	
	/********************************************/
		/* Attributs de la classe INGREDIENT */
	/********************************************/
	
	// Stock est la variable qui contient le stock courant de l'ingr�dient
	// Restock est la variable qui contient la quantit� qui est ajout�e au stock
	// xt correspond � la coordonn�e x de l'ingr�dient dans le t�l�phone
	// yt correspond � la coordonn�e y de l'ingr�dient dans le t�l�phone
	private int stock, restock, xt, yt;
	
	// stock_init correspond au stock intial de l'ingr�dient.
	// SEUIL_RESTOCK correpond au seuil en dessous duquel on restocke l'ingr�dients.
	private final int STOCK_INIT, SEUIL_RESTOCK;
	
	// TELEPHONE_X et TELEPHONE_Y correspondent au coordon�es auxquelles se trouve le t�l�phone
	private static int TELEPHONE_X = 580;
	private static int TELEPHONE_Y = 350;
	
	// Contient la valeur qui correpond au pixel gris� lorsque l'on essaye de commander des
	// ingr�dients. (Pour tous les ingr�dients sauf le riz)
	private static int RGB_GRISE = -9602177;
	
	// Chemin de boutons sur lesquels il faut cliquer pour restocker l'ingr�dient.
	private CheminBouton <Bouton> cheminRestock;
	
	// Repere qui repr�sente le coin gauche sup�rieur de la fen�tre de jeu, � partir du quel
	// on calcule la position des diff�rents objets du jeu sur lesquels il faut cliquer dans la 
	// fen�tre enti�re
	private static Repere r;
	// rgb correspond à la couleur du pixel quand on essaie de commander l'ingrédient à l'aide du téléphone
	// et que la case est grisée (pas assez d'argent)
	private int rgb;

	// Nom de l'ingr�dient
	private String nom;
	
	/********************************************/
		/* Constructeurs de la classe Ingr�dient */
	/********************************************/

	// Constructeur utilis� pour tous les ingr�dients sauf le riz.
	public Ingredient(String nom, int x, int y, RobotRepere robrep, int stock, int restock,
			CheminBouton <Bouton> chemin, int xt, int yt, int seuil) {
		super(x, y, robrep);
		this.nom = nom;
		this.stock = stock;
		this.STOCK_INIT = stock;
		this.cheminRestock = chemin;
		this.cheminRestock.add(0, new Bouton(TELEPHONE_X, TELEPHONE_Y, robrep));
		this.restock = restock;
		this.SEUIL_RESTOCK = seuil;
		this.xt = xt;
		this.yt = yt;
		this.r = robrep.getRepere();
		this.rgb = this.RGB_GRISE ;
		//this.wait = wait;
	}

	// Constructeur utilis� pour le riz car la couleur du pixel gris� est diff�rente,
	// de celle pour les autres ingr�dients.
	public Ingredient(String nom, int x, int y, RobotRepere robrep, int stock, int restock,
			CheminBouton <Bouton> chemin, int xt, int yt, int rgb, int seuil) {
		super(x, y, robrep);
		this.nom = nom;
		this.stock = stock;
		this.STOCK_INIT = stock;
		this.cheminRestock = chemin;
		this.cheminRestock.add(0, new Bouton(580, 350, robrep));
		this.restock = restock;
		this.SEUIL_RESTOCK = seuil;
		this.xt = xt;
		this.yt = yt;
		this.r = robrep.getRepere();
		this.rgb = rgb;
		
	}

	/********************************************/
		/* M�thodes de la classe Ingr�dient */
	/********************************************/
	
	/**
	 * Méthode qui clique sur l'ingrédient et décrémente le stock
	 */
	public void clicGauche() {
		super.clicGauche();
		this.stock--;
	}


	/**
	 * 
	 * @return stock courant de l'ingrédient
	 */
	public int getStock() {
		return this.stock;
	}


	/*public boolean restockafter() throws IOException {
		// On parcourt tous les boutons qui permettent de commander l'ingrédient
		for (Bouton b : this.cheminRestock) {
			// On bouge la souris pour qu'elle ne gène pas lorsqu'on fait un screenshot pour savoir si on peut commander
			// l'ingrédient ou pas (ingrédient grisé quand pas assez d'argent)
			this.robot.mouseMove(xt + 40, yt + 40);
			BufferedImage shot = this.robot.createScreenCapture(new Rectangle(
					this.r.getCoord_x(), this.r.getCoord_y(), 640, 480));
			int n = shot.getRGB(this.xt, this.yt);
			// Si le pixel est grisé on quitte le téléphone et on renvoie faux
			if (n == this.rgb) {
				System.out.println("Probleme");
				clicGauche(this.r.getCoord_x() + 593,
						this.r.getCoord_y() + 333, this.robot);
				return false;

			}
			// Sinon on clique sur le bouton
			b.clicGauche();
		}
		System.out.println("Restockage terminé");
		//On incrémente le stock et on renvoie true
		this.stock += this.restock;
		return true;
	}*/
	/**
	 * Méthode qui restocke un ingrédient
	 * @return vrai si le restockage a eu lieu, faux sinon
	 * @throws IOException
	 */
	public boolean restock() throws IOException {
		// On parcourt tous les boutons qui permettent de commander l'ingrédient
		for (Bouton b : this.cheminRestock) {
			// On bouge la souris pour qu'elle ne gène pas lorsqu'on fait un screenshot pour savoir si on peut commander
			// l'ingrédient ou pas (ingrédient grisé quand pas assez d'argent)
			this.robot.mouseMove(xt + 40, yt + 40);
			this.robot.delay(200);
			BufferedImage shot = this.robot.createScreenCapture(new Rectangle(
					this.r.getCoord_x(), this.r.getCoord_y(), 640, 480));
			int n = shot.getRGB(this.xt, this.yt);
			// Si le pixel est grisé on quitte le téléphone et on renvoie faux
			if (n == this.rgb) {
				System.out.println("Probleme");
				clicGauche(this.r.getCoord_x() + 593,
						this.r.getCoord_y() + 333, this.robot);
				return false;

			}
			// Sinon on clique sur le bouton
			b.clicGauche();
		}
		System.out.println("Restockage terminé");
		//On incrémente le stock et on renvoie true
		this.stock += this.restock;
		return true;



	}


	public static boolean restock (Recette recette, boolean apresmoitie,
			Robot r, EnsClients e) throws IOException{
		boolean stock = false;
		// Si on approche de la fin du jeu, on change la technique pour restocker.
		// ie. on restocke si il n'y a pas assez d'ingrédients avant de faire un sushi.
		//if (!apresmoitie && !avant){
		for (Ingredient i : recette){
			if (i.getStock() < 2){
				System.out.println("Stock Insuffisant");
				if (!i.restock()) return false;
				stock = true;
			}
		}
		if (stock){
			r.delay(5500);
		}
		return true;
	
		}
	// Sinon on restocke après avoir fait le sushi si un des ingrédients a un niveau inférieur au seuil, 
	// ou avant de faire un sushi si il n'y a pas assez d'ingrédients pour faire le sushi.

	/*else {
			for (Ingredient i : recette){
				if (i.getStock()< 2){
					if(!i.restockafter()) return false;
					stock = true;
				}
			}

			if (stock) {
				e.debarasse();
				r.delay(6000);
			}
			return true;
		}
	}*/
	/**
	 * Méthode qui reset le stock (utile lorsque qu'on passe à un autre niveau)
	 */
	public void resetStock() {
		this.stock = this.STOCK_INIT;
	}

	/** 
	 * Méthode qui renvoie le seuil à partir du quel on doit commander l'ingrédient
	 * @return
	 */
	public int getSeuil(){
		return this.SEUIL_RESTOCK;
	}

	/**
	 * Méthode qui vérifie si le stock est suffisant pour chaque ingrédient de la liste passée en paramètre,
	 * et restocke si le stock n'est pas suffisant
	 * @param liste
	 * @throws IOException
	 */
	public static void verifStock(ArrayList<Ingredient> liste)
			throws IOException {
		System.out.println("Je vérifie le stock");
		for (Ingredient i : liste) {
			System.out.println("stock : " + i.getStock());
			i.restock();
		}
	}

}
