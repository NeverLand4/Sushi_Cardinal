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
	
	// Stock est la variable qui contient le stock courant de l'ingrédient
	// Restock est la variable qui contient la quantité qui est ajoutée au stock
	// xt correspond à la coordonnée x de l'ingrédient dans le téléphone
	// yt correspond à la coordonnée y de l'ingrédient dans le téléphone
	private int stock, restock, xt, yt;
	
	// stock_init correspond au stock intial de l'ingrédient.
	// SEUIL_RESTOCK correpond au seuil en dessous duquel on restocke l'ingrédients.
	private final int STOCK_INIT, SEUIL_RESTOCK;
	
	// TELEPHONE_X et TELEPHONE_Y correspondent au coordonées auxquelles se trouve le téléphone
	private static int TELEPHONE_X = 580;
	private static int TELEPHONE_Y = 350;
	
	// Contient la valeur qui correpond au pixel grisé lorsque l'on essaye de commander des
	// ingrédients. (Pour tous les ingrédients sauf le riz)
	private static int RGB_GRISE = -9602177;
	
	// Chemin de boutons sur lesquels il faut cliquer pour restocker l'ingrédient.
	private CheminBouton <Bouton> cheminRestock;
	
	// Repere qui représente le coin gauche supérieur de la fenêtre de jeu, à partir du quel
	// on calcule la position des différents objets du jeu sur lesquels il faut cliquer dans la 
	// fenêtre entière
	private static Repere r;
	// rgb correspond Ã  la couleur du pixel quand on essaie de commander l'ingrÃ©dient Ã  l'aide du tÃ©lÃ©phone
	// et que la case est grisÃ©e (pas assez d'argent)
	private int rgb;

	// Nom de l'ingrédient
	private String nom;
	
	/********************************************/
		/* Constructeurs de la classe Ingrédient */
	/********************************************/

	// Constructeur utilisé pour tous les ingrédients sauf le riz.
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

	// Constructeur utilisé pour le riz car la couleur du pixel grisé est différente,
	// de celle pour les autres ingrédients.
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
		/* Méthodes de la classe Ingrédient */
	/********************************************/
	
	/**
	 * MÃ©thode qui clique sur l'ingrÃ©dient et dÃ©crÃ©mente le stock
	 */
	public void clicGauche() {
		super.clicGauche();
		this.stock--;
	}


	/**
	 * 
	 * @return stock courant de l'ingrÃ©dient
	 */
	public int getStock() {
		return this.stock;
	}


	/*public boolean restockafter() throws IOException {
		// On parcourt tous les boutons qui permettent de commander l'ingrÃ©dient
		for (Bouton b : this.cheminRestock) {
			// On bouge la souris pour qu'elle ne gÃ¨ne pas lorsqu'on fait un screenshot pour savoir si on peut commander
			// l'ingrÃ©dient ou pas (ingrÃ©dient grisÃ© quand pas assez d'argent)
			this.robot.mouseMove(xt + 40, yt + 40);
			BufferedImage shot = this.robot.createScreenCapture(new Rectangle(
					this.r.getCoord_x(), this.r.getCoord_y(), 640, 480));
			int n = shot.getRGB(this.xt, this.yt);
			// Si le pixel est grisÃ© on quitte le tÃ©lÃ©phone et on renvoie faux
			if (n == this.rgb) {
				System.out.println("Probleme");
				clicGauche(this.r.getCoord_x() + 593,
						this.r.getCoord_y() + 333, this.robot);
				return false;

			}
			// Sinon on clique sur le bouton
			b.clicGauche();
		}
		System.out.println("Restockage terminÃ©");
		//On incrÃ©mente le stock et on renvoie true
		this.stock += this.restock;
		return true;
	}*/
	/**
	 * MÃ©thode qui restocke un ingrÃ©dient
	 * @return vrai si le restockage a eu lieu, faux sinon
	 * @throws IOException
	 */
	public boolean restock() throws IOException {
		// On parcourt tous les boutons qui permettent de commander l'ingrÃ©dient
		for (Bouton b : this.cheminRestock) {
			// On bouge la souris pour qu'elle ne gÃ¨ne pas lorsqu'on fait un screenshot pour savoir si on peut commander
			// l'ingrÃ©dient ou pas (ingrÃ©dient grisÃ© quand pas assez d'argent)
			this.robot.mouseMove(xt + 40, yt + 40);
			this.robot.delay(200);
			BufferedImage shot = this.robot.createScreenCapture(new Rectangle(
					this.r.getCoord_x(), this.r.getCoord_y(), 640, 480));
			int n = shot.getRGB(this.xt, this.yt);
			// Si le pixel est grisÃ© on quitte le tÃ©lÃ©phone et on renvoie faux
			if (n == this.rgb) {
				System.out.println("Probleme");
				clicGauche(this.r.getCoord_x() + 593,
						this.r.getCoord_y() + 333, this.robot);
				return false;

			}
			// Sinon on clique sur le bouton
			b.clicGauche();
		}
		System.out.println("Restockage terminÃ©");
		//On incrÃ©mente le stock et on renvoie true
		this.stock += this.restock;
		return true;



	}


	public static boolean restock (Recette recette, boolean apresmoitie,
			Robot r, EnsClients e) throws IOException{
		boolean stock = false;
		// Si on approche de la fin du jeu, on change la technique pour restocker.
		// ie. on restocke si il n'y a pas assez d'ingrÃ©dients avant de faire un sushi.
		//if (!apresmoitie && !avant){
		for (Ingredient i : recette){
			if (i.getStock() < i.getSeuil()){
				System.out.println("Stock Insuffisant");
				if (!i.restock()) return false;
				stock = true;
			}
		}
		if (stock){
			r.delay(3800);
		}
		return true;
	
		}
	// Sinon on restocke aprÃ¨s avoir fait le sushi si un des ingrÃ©dients a un niveau infÃ©rieur au seuil, 
	// ou avant de faire un sushi si il n'y a pas assez d'ingrÃ©dients pour faire le sushi.

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
	 * MÃ©thode qui reset le stock (utile lorsque qu'on passe Ã  un autre niveau)
	 */
	public void resetStock() {
		this.stock = this.STOCK_INIT;
	}

	/** 
	 * MÃ©thode qui renvoie le seuil Ã  partir du quel on doit commander l'ingrÃ©dient
	 * @return
	 */
	public int getSeuil(){
		return this.SEUIL_RESTOCK;
	}

	/**
	 * MÃ©thode qui vÃ©rifie si le stock est suffisant pour chaque ingrÃ©dient de la liste passÃ©e en paramÃ¨tre,
	 * et restocke si le stock n'est pas suffisant
	 * @param liste
	 * @throws IOException
	 */
	public static void verifStock(ArrayList<Ingredient> liste)
			throws IOException {
		System.out.println("Je vÃ©rifie le stock");
		for (Ingredient i : liste) {
			System.out.println("stock : " + i.getStock());
			i.restock();
		}
	}

}
