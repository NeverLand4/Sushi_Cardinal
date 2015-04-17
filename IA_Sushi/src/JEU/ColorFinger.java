package JEU;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.imageio.ImageIO;

import Ecran.Bouton;
import Ecran.Image;
import Ecran.Repere;

/** COULEURS **/
/*
 * vert -9913280
rouge -1565664
jaune -462848
bleu -12012320
scanner Ã  200 px (y)
 */

public class ColorFinger extends Cardinal{

	/********************************************/
		
		/* Attributs de la classe ColorFinger */
	
	/********************************************/
	
	// Les différents boutons du jeu
	private Bouton vert;
	private Bouton rouge;
	private Bouton bleu;
	private Bouton jaune;
	private Bouton joue;
	
	/********************************************/
	
		/* Constructeur de la classe ColorFinger */

	/********************************************/
	
	public ColorFinger(Robot r, Repere rep) throws IOException,
	URISyntaxException 
	{
		
		super(r, rep, 640, 842, "ColorFinger.png");
		joue = new Bouton (300, 600, this.robotrep);
		vert = new Bouton(80, 650, this.robotrep);
		rouge = new Bouton(230, 650, this.robotrep);
		jaune = new Bouton(550, 650, this.robotrep);
		bleu = new Bouton(400, 650, this.robotrep);

		
	}
	
	
	/********************************************/
	
		/* Méthodes de la classe ColorFinger */

	/********************************************/
	
	/**
	 * Modification de méthode trouveFenêtre héritée
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
			this.robotrep.setRepere(new Repere(coin[0]-166, coin[1]-411));
			//this.robotrep.getRobot().mouseMove(this.robotrep.getRepere().getCoord_x(), this.robotrep.getRepere().getCoord_y());
			return true;
		}
	}
	
	/**
	 * Initialise les différents boutons 
	 */
	public void initBoutons(){
		joue = new Bouton (300, 600, this.robotrep);
		vert = new Bouton(80, 650, this.robotrep);
		rouge = new Bouton(230, 650, this.robotrep);
		jaune = new Bouton(550, 650, this.robotrep);
		bleu = new Bouton(400, 650, this.robotrep);
	}

	/**
	 * Commence le jeu en appuyant sur le bouton play 
	 */
	public void commenceJeu() throws IOException, URISyntaxException{
		this.ouvreFenetre("http://www.newgrounds.com/portal/view/642967");
		this.robotrep.getRobot().delay(10000);
		this.robotrep.getRobot().mouseWheel(9);this.trouveFenetre();
		this.robotrep.getRobot().mouseMove(this.robotrep.getRepere().getCoord_x() ,
				this.robotrep.getRepere().getCoord_y());
		initBoutons();
		joue.clicGauche();
		joue.clicGauche();
		joue.clicGauche();
		joue.clicGauche();
		this.robotrep.getRobot().delay(3000);
		vert.dragTO(90, 200);
		
		this.robotrep.getRobot().delay(4000);
	}
	
	/**
	 * Méthode qui scanne une ligne et fait un drag and drop vers le pixel si il est coloré
	 * @param n
	 */
	public void scanneLigne (int n){
		BufferedImage im = this.robotrep.getRobot().createScreenCapture(new Rectangle(
				this.robotrep.getRepere().getCoord_x(), this.robotrep.getRepere().getCoord_y()+n,640,1));
		//ImageIO.write(im,
			//	"png", new File ("testjeu"+j+".png"));
		for (int k = 0 ; k < 1 ; k ++){
		 for (int i = 10 ; i < 640 ; i = i + 100){
			 /* vert -9913280
			 rouge -1565664
			 jaune -462848
			 bleu -12012320*/
			/*System.out.println(i+" "+k+" "+im.getRGB(i, k));*/
			if (im.getRGB(i, k)==-9913280) {
				this.vert.dragTO(i,200+k);
				break;
				}
			else if (im.getRGB(i, k)==-1565664){
				this.rouge.dragTO(i, 200+k);
				break;
			}
							
			else if (im.getRGB(i, k)==-462848) {
				this.jaune.dragTO(i, 200+k);
				break;
			}
							
			else if (im.getRGB(i, k)==-12012320) {
				this.bleu.dragTO(i, 200+k);
				break;
				}
			}

		}
		this.robotrep.getRobot().delay(200);
	}
	/**
	 * Méthode qui joue au jeu ColorFinger
	 */
	public  void joue (){
		for (int i = 0 ; i < 5000 ; i++){
			this.scanneLigne(400);
			this.scanneLigne(300);
			this.scanneLigne(200);
			this.scanneLigne(100);
			this.scanneLigne(5);
		}
	}
	
	}