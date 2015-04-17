package JEU;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Rectangle;
import java.awt.Robot;
import java.io.IOException;
import java.awt.Desktop;
import java.net.URI;
import java.net.URISyntaxException;
import Client.EnsClients;
import Ecran.*;
import Exception.JeuFiniException;
import Ingredient.Ingredient;
import Niveaux.Niveau1;

public class SushiGoRound {
	
	/********************************************/
		/* Attributs de la classe SushiGoRound*/
	/********************************************/
	
	// Coordon�es des boutons pour commencer le jeu
	private static final int JOUER_X = 320;
	private static final int JOUER_Y = 195;
	private static final int CONTINUER_X = 328;
	private static final int CONTINUER_Y = 389;
	private static final int SKIP_X = 540;
	private static final int SKIP_Y = 450;
	
	// D�lai entre les screenshot quand on essaie de trouver la fenetre de jeu
	protected static final int DELAY_SCREENSHOT = 1000;
	
	// Nombre de fois ou l'on cherche la fen�tre de jeu avant d'arr�ter l'�xecution
	protected static final int SEUIL_CHERCHE_JEU = 120;
	
	// Coordonn�es de l'image qui indique qu'on passe au niveau suivant
	private static final int GAGNE_X = 173;
	private static final int GAGNE_Y = 366;
	
	// Coordonn�es de l'image qui indique qu'on a perdu
	private static final int PERDU_X = 285;
	private static final int PERDU_Y = 110;
	
	// Repere + robot
	protected RobotRepere robotrep;
	
	// Image qu'on doit trouver dans la fenetre de jeu pour passer au niveau suivant
	private static Image continuee = Image.readIm("continuer.png");
	
	// Image qu'on doit trouver dans la fen�tre de jeu si on a perdu
	private static Image perdu = Image.readIm("perdu.png");
	
	// Coin sup�rieur droit de la fen�tre de jeu que l'on cherche au d�but de l'exec du prog
	protected  Image coinJeu = Image.readIm("Init_recon.png");
	
	// Bool�en qui est vrai si on est proche de la fin du jeu
	private boolean procheFin = false;
	
	// Indique les dimensions de la fen�tre de jeu
	protected int dim_jeu_x;
	protected int dim_jeu_y;
	
	// Indique les dimensions de la fenetre de jeu quand la hauteur = largeur
	protected int taille;
	
	// Dimensions des imgages import�es
	private static final int IM_DIMENSIONS = 20;
	
	// On cr�e des boutons pour passer au niveau suivant
	private Bouton jouer, continuer, skip;
	
	// On commence au niveau 1
	private Niveau1 niveau;
	
	// Dimension de l'�cran
	protected Dimension screenSize;
	
	// Ensemble des clients du jeu
	private EnsClients ensclients;

	
	/********************************************/
		/* Contructeur de la classe SushiGoRound*/
	/********************************************/
	public SushiGoRound(Robot r, Repere rep) throws IOException, URISyntaxException {

		robotrep = new RobotRepere(r, rep);
		niveau = new Niveau1(robotrep);
		ensclients = new EnsClients(robotrep, niveau.getListeSushi());
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		screenSize = new Dimension (1366, 768);
		jouer = new Bouton(JOUER_X, JOUER_Y, robotrep);
		continuer = new Bouton(CONTINUER_X, CONTINUER_Y, robotrep);
		skip = new Bouton(SKIP_X, SKIP_Y, robotrep);
		taille = 640;
		
	}
	

	/**
	 * M�thode qui renvoie vrai si on est � la fin du niveau
	 * @param Robot
	 * @param repere
	 * @return boolean fin niveau
	 */
	public static boolean finNiveau (Robot r, Repere rep){
		if (new Image(r.createScreenCapture(new Rectangle(rep
				.getCoord_x() + GAGNE_X, rep.getCoord_y() + GAGNE_Y, IM_DIMENSIONS, IM_DIMENSIONS)))
		.equals(continuee)) return true;
		if (new Image(r.createScreenCapture(new Rectangle(rep
				.getCoord_x() + PERDU_X, rep.getCoord_y() + PERDU_Y, IM_DIMENSIONS, IM_DIMENSIONS)))
		.equals(perdu)) return true;
		return false;
	}

	/**
	 * Méthode qui ouvre la fenêtre de jeu dans le navigateur par défaut
	 * 
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	public void ouvreFenetre() throws IOException, URISyntaxException {
		Desktop.getDesktop()
		.browse(new URI(
				"http://www.miniclip.com/games/sushi-go-round/fr/focus/"));
	}
	
	public void ouvreFenetre(String adresse) throws IOException, URISyntaxException {
		Desktop.getDesktop()
		.browse(new URI(adresse));
	}
	
	public Niveau1 getNiveau (){
		return this.niveau;
	}
	
	/**
	 * M�thode qui r�initialise certains attributs
	 * @throws IOException
	 */
	public void reinit_Repere() throws IOException{
		niveau = new Niveau1(robotrep);
		ensclients = new EnsClients(robotrep, niveau.getListeSushi());
		jouer = new Bouton(JOUER_X, JOUER_Y, robotrep);
		continuer = new Bouton(CONTINUER_X, CONTINUER_Y, robotrep);
		skip = new Bouton(SKIP_X, SKIP_Y, robotrep);
	}

	/**
	 * Méthode qui cherche la fenêtre du jeu Sushi Go Round sur l'écran.
	 * 
	 * @return vrai si la fenêtre est trouvée avant que 2 minutes passent, faux
	 *         sinon.
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	public boolean trouveFenetre() throws IOException, URISyntaxException {
		Image screenshot;
		int cpt = 0;
		int corner_px = -1;
		do{
			System.out.println("Cherche la fenetre de jeu");
			screenshot = new Image(this.robotrep.getRobot().createScreenCapture(new Rectangle(screenSize)));
			corner_px = screenshot.trouveDansImage(coinJeu);
			cpt++;
			this.robotrep.getRobot().delay(DELAY_SCREENSHOT);
		}while (corner_px == -1 && cpt < SEUIL_CHERCHE_JEU);
		
		if (cpt == SEUIL_CHERCHE_JEU) return false;
		else {
			int[] coin = Image.computeImageCorner(corner_px, coinJeu, screenshot,this.taille);
			this.robotrep.setRepere(new Repere(coin[0], coin[1]));
			this.robotrep.getRobot().mouseMove(this.robotrep.getRepere().getCoord_x(), this.robotrep.getRepere().getCoord_y());
			return true;
		}
	}
	

	/**
	 * Méthode qui démarre le jeu en cliquant sur différents boutons
	 * 
	 * @throws IOException
	 * @throws URISyntaxException 
	 */
	public void commenceJeu() throws IOException, URISyntaxException {
		jouer.clicGauche();
		jouer.clicGauche();
		continuer.clicGauche();
		continuer.clicGauche();
		skip.clicGauche();
		skip.clicGauche();
		continuer.clicGauche();
		continuer.clicGauche();

	}
	
	
	/**
	 * M�thode qui met l'attribut procheFin � vrai si on est proche d el a fin du niveau
	 * Pour savoir on regarde la barre d'avancement en haut � droite de l'�cran de jeu.
	 */
	public void checkTime(){
		if (!procheFin){
			Color c = this.robotrep.getRobot().getPixelColor(this.robotrep.getRepere().getCoord_x()+585,
					this.robotrep.getRepere().getCoord_y()+16);
			if (!c.equals(c.WHITE)) {
				System.out.println("couleur différente");
			procheFin = true;
			}
		}
	}


	/**
	 * Méthode qui passe au niveau suivant, qui remet les stock au niveau initial et qui
	 * met à jour la liste des Sushi
	 * 
	 * @throws IOException
	 * @throws JeuFiniException 
	 */
	public void nextLevel() throws IOException, JeuFiniException {
		niveau = niveau.nextLevel();
		for (Ingredient i : niveau.getListeIngredients()) {
			i.resetStock();
		}
		procheFin = false;
		ensclients.modListe(niveau.getListeSushi());
	}

	/** 
	 * Méthode qui vérifie si le niveau est gagné. Si c'est le cas, on passe au niveau suivant.
	 * @throws IOException
	 * @throws JeuFiniException 
	 */
	public void verifGagne() throws IOException, JeuFiniException {
		if (new Image(this.robotrep.getRobot().createScreenCapture(new Rectangle(this.robotrep.getRepere()
				.getCoord_x() + GAGNE_X, this.robotrep.getRepere().getCoord_y() + GAGNE_Y, IM_DIMENSIONS, IM_DIMENSIONS)))
		.equals(continuee)) {
			this.nextLevel();
			this.robotrep.getRobot().delay(10000);
			continuer.clicGauche();
			continuer.clicGauche();
			continuer.clicGauche();
			continuer.clicGauche();
		}
	}

	/**
	 * Méthode qui vérifie si on a perdu. Si c'est le cas elle lance une exception qui interrompt l'éxecution du programme
	 * @throws IOException
	 */
	public void verifPerdu() throws IOException {
		if (new Image(this.robotrep.getRobot().createScreenCapture(new Rectangle(this.robotrep.getRepere()
				.getCoord_x() + PERDU_X, this.robotrep.getRepere().getCoord_y() + PERDU_Y, IM_DIMENSIONS, IM_DIMENSIONS)))
		.equals(perdu)) {
			System.out.println("perdu");
			throw new IOException();
		}
	}
	
	/**
	 * Méthode qui réalise un tour du jeu Sushi Go Round : scanne et réalise les commandes des clients, débarasse, et vérifie
	 * si on a gagné ou perdu le niveau courant.
	 * @throws IOException
	 * @throws JeuFiniException 
	 */
	public void tourJeu(int n) throws IOException, JeuFiniException {
		/*Bouton t_topping = new Bouton(515, 270, this.robotrep);
		Bouton t_free = new Bouton(520, 290, this.robotrep);
		 Bouton t_unagi = new Bouton(548, 207, this.robotrep);
		 CheminBouton stock_unagi = new CheminBouton<Bouton>(
				t_topping, t_unagi, t_free);
		Ingredient unagi = new Ingredient(90, 440, this.robotrep, 5, stock_unagi, 524,
				315, 4, true);
		unagi.restock();*/
		/*ImageIO.write(this.robotrep.getRobot().createScreenCapture(new Rectangle (this.robotrep.getRepere().getCoord_x()+166,
				this.robotrep.getRepere().getCoord_y()+346, 100, 100)),"png", new File ("tapis"+n+".png"));
		this.robotrep.getRobot().delay(2000);*/
		
		ensclients.chacunSonTour(this.procheFin, this);
		checkTime();
		//ensclients.debarasse();
		verifGagne();
		checkTime();
		verifPerdu();
	}
	
	/**
	 * Méthode qui joue au jeu Sushi Go Round. 
	 * @throws IOException
	 * @throws JeuFiniException 
	 * @throws URISyntaxException 
	 */
	public void joue() throws IOException, JeuFiniException, URISyntaxException {
		this.reinit_Repere();
		this.commenceJeu();
		int n = 0;
		while (true) {
			tourJeu(n);
			n++;
		}
	}



	

}