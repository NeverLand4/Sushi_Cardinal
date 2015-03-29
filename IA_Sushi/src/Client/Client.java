package Client;

import java.awt.Rectangle;
import java.awt.Robot;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import Ecran.*;
import Sushi.*;
public class Client {
	
	
	/********************************************/
		/* Attributs de la classe CLIENT */
	/********************************************/
	
	// Représente la taille du screenshot (20x20), que l'on fait pour trouver la commande
	// du client
	private static int taille_image = 20;
	
	// Les coordonées x et y du client. Elles correspondent aux coordonnées dans la fenêtre du
	// jeu à partir desquelles on récupère l'image du sushi commandé par le client. La coordonnée
	// y est static car elle est la même pour tous les clients.
	private static int y = 47;
	private int x;
	

	// Ces variables représentent la distance à laquelle se trouve l'assiette par rapport à la 
	// commande du client
	private static int x_assiette = 30;
	private static int y_assiette = 150;
	
	// Attribut qui contient la commande du client. Si on a pas encore lu la commande du client,
	// ou que l'on vient de servir la commande, cet attribut contient la valeur null.
	private Sushi s;
	
	// Robot qui permet de cliquer dans la fenêtre de jeu pour faire les commandes.
	private Robot r;
	
	// Repere qui représente le coin gauche supérieur de la fenêtre de jeu, à partir du quel
	// on calcule la position des différents objets du jeu sur lesquels il faut cliquer dans la 
	// fenêtre entière
	private Repere rep;
	
	// Bouton qui représente l'assiette du client
	private Bouton assiette;
	
	// Cet attribut peut être utilisé pour récupérer une partie du visage du client, pour faire
	// des statistiques sur le type de client et leur commandes (PAS ENCORE IMPLEMENTE)
	private Image image;
	private static int cpt;

	/*********************************************/
		/* Constructeur de la classe client */
	/********************************************/

	
	public Client (int x,RobotRepere r){
		this.x = x;
		this.r = r.getRobot();
		this.rep = r.getRepere();
		this.s = null;
		this.assiette = new Bouton (x + this.x_assiette, this.y + this.y_assiette, r);
	    this.image = null;
	}
	
	/********************************************/
		/* Méthodes de la classe client */
	/********************************************/

	
	/**
	 * Methode qui prend un screenshot de la commande du client de taille 20x20 pixels
	 * @return screenshot de la commande du client (Image)
	 */
	public Image imClient (){
		return (new Image (this.r.createScreenCapture
				(new Rectangle (this.rep.getCoord_x()+x, this.rep.getCoord_y()+
						this.y,this.taille_image,this.taille_image))));
		}


	/**
	 * Méthode qui met la commande du client dans l'attribut s de type Sushi
	 * @param commande du client
	 */
	public void setSushi (Sushi s){
		this.s = s;
	}
	

	/**
	 * MÃ©thode qui rÃ©alise un sushi
	 * @param ensemble de clients
	 * @throws IOException
	 */
	public void makeSushi (EnsClients e, boolean moitie) throws IOException{
		// Si le sushi est différent de null, on fait le sushi puis on le remet à null
		if (this.s!=null){
			this.s.fait(this.r, this.rep, this, e, moitie);
			this.s = null;
		}
	}
	
	
	/**
	 * Méthode qui renvoie l'attribut Sushi du client
	 * @return commande du client
	 */
	
	public Sushi getSushi (){
		return this.s;
	}
	
	
	/**
	 * MÃ©thode qui clique sur l'assiette du client
	 */
	public void clicAssiette(){
		this.assiette.clicGauche();
	}
	
	
	
	/********************************************/
	 /* Méthodes pas utilisées dans le programme*/
	/********************************************/

	
	/**
	 * Méthode qui crée une image png, qui contient le visage du client
	 */
	public void getIMClient(){
		try{
		this.cpt++;
		ImageIO.write(this.r.createScreenCapture(new Rectangle(this.rep.getCoord_x()+x, this.rep.getCoord_y()+y+106,10, 10)), 
				"PNG", new File ("Client"+cpt+".png"));
		}
		catch (IOException e){
			System.out.println("Impossible de prendre une photo du client");
		}
	}
	
	/**
	 * Méthode qui met dans l'attribut image, le screenshot du visage du client.
	 */
	public void setIM (){
		this.image = new Image (this.r.createScreenCapture(new Rectangle(this.rep.getCoord_x()+x, this.rep.getCoord_y()+y+106,10, 10)));
	}
	
	/**
	 * Méthode qui retourne un screenshot du visage du client.
	 * @return Image 
	 */
	public Image shotIMC (){
		return new Image (this.r.createScreenCapture(new Rectangle(this.rep.getCoord_x()+x, this.rep.getCoord_y()+y+106,10, 10)));
	}
	
	/**
	 * Méthode qui retourne le contenu de l'attribut Image du client.
	 * @return Image
	 */
	public Image getImage (){
		return this.image;
	}
	



}
