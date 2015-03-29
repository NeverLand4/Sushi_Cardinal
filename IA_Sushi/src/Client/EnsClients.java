package Client;

import java.awt.Robot;
import java.io.IOException;
import java.util.ArrayList;

import Ecran.*;
import JEU.SushiGoRound;
import Sushi.*;

public class EnsClients {
	

	/********************************************/
		/* Attributs de la classe ENSCLIENT */
	/********************************************/

	// Les coordonnées x de chaque client, qui sont static, car elle restent toujours les mêmes
	// dans le jeu.
	private static int client1_x = 47;
	private static int client2_x = 148;
	private static int client3_x = 249;
	private static int client4_x = 350;
	private static int client5_x = 451;
	private static int client6_x = 552;
	
	// Delai entre le moment ou on a fini de faire la commande d'un client, et le moment où on 
	// lit la commande du client suivant
	private static int delai_entre_client = 10;
	
	// Les 6 clients du jeu sont stockés dans une ArrayList
	private ArrayList <Client> clients ;
	
	// Repere qui représente le coin gauche supérieur de la fenêtre de jeu, à partir du quel
	// on calcule la position des différents objets du jeu sur lesquels il faut cliquer dans la 
	// fenêtre entière
	private Repere rep;
	
	// Robot qui permet de cliquer dans la fenêtre de jeu pour faire les commandes.
	private Robot r;
	
	// Attribut qui contient la liste des sushis du niveau en cours
	private Liste_Sushi liste ;
	
	/********************************************/
	/* Constructeur de la classe EnsClients */
	/********************************************/

	public EnsClients (RobotRepere r, Liste_Sushi liste) throws IOException{

		this.clients = new ArrayList <Client> ();
		this.rep = r.getRepere();
		this.r = r.getRobot();
		// On crée les six clients du jeu SushiGoRound.
		Client c1 = new Client (client1_x, r);
		Client c2 = new Client (client2_x,r);	
		Client c3 = new Client (client3_x, r);
		Client c4 = new Client (client4_x, r);
		Client c5 = new Client (client5_x, r);
		Client c6 = new Client (client6_x, r);
		// On ajoute les clients à la liste.
		this.clients.add(c1);
		this.clients.add(c2);
		this.clients.add(c3);
		this.clients.add(c4);
		this.clients.add(c5);
		this.clients.add(c6);
		this.liste = liste;
	
	}
	
	/********************************************/
	/* Méthodes de la classe Ensclient */
	/********************************************/



	/**
	 * MÃ©thode qui pour chaque client scanne puis rÃ©alise la commande.
	 * @throws IOException
	 */
	public void chacunSonTour (boolean moitie, SushiGoRound j) throws IOException{
		// Compteur qui permet de débarrasser après avoir servi deux clients
		int cpt = 0;
		// On parcourt tous les clients
		for (Client c : this.clients){
			cpt ++;
			// Pour chaque client on parcourt la liste des sushis du niveau en cours,
			// jusqu'à trouver le sushi qui correspond à la commande. Si aucun sushi ne correspond,
			// c'est qu'il n'y a pas de client ou que le client est entrain de manger.
			for (Sushi s : this.liste){
				if (c.getSushi() == null && s.reconnait(c)){
					// Si un des sushis correspond, on fait le sushi, et on sort de la boucle for
					c.setSushi(s);
					c.makeSushi(this,  moitie);
					break;
				}
			} 
			// On débarasse tous les deux clients parcourus.
			if (cpt == 3) this.debarasse();
			this.r.delay(this.delai_entre_client);
			
			/** PAS UTILISE **/
			// Vérifie si le jeu est proche de la fin 
			//j.checkTime();
		}
		this.debarasse();
	}

	/** 
	 * Modifie la liste des sushi
	 * @param liste des sushi
	 */
	public void modListe (Liste_Sushi s){
		this.liste = s;
	}

	/**
	 * MÃ©thode qui clique sur les assiettes de tous les clients
	 */
	public void debarasse (){
		for (Client c : this.clients){
			c.clicAssiette();
		}
	}



}
