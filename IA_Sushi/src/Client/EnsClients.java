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

	// Les coordonn�es x de chaque client, qui sont static, car elle restent toujours les m�mes
	// dans le jeu.
	private static int client1_x = 47;
	private static int client2_x = 148;
	private static int client3_x = 249;
	private static int client4_x = 350;
	private static int client5_x = 451;
	private static int client6_x = 552;
	
	// Delai entre le moment ou on a fini de faire la commande d'un client, et le moment o� on 
	// lit la commande du client suivant
	private static int delai_entre_client = 10;
	
	// Les 6 clients du jeu sont stock�s dans une ArrayList
	private ArrayList <Client> clients ;
	
	// Repere qui repr�sente le coin gauche sup�rieur de la fen�tre de jeu, � partir du quel
	// on calcule la position des diff�rents objets du jeu sur lesquels il faut cliquer dans la 
	// fen�tre enti�re
	private Repere rep;
	
	// Robot qui permet de cliquer dans la fen�tre de jeu pour faire les commandes.
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
		// On cr�e les six clients du jeu SushiGoRound.
		Client c1 = new Client (client1_x, r);
		Client c2 = new Client (client2_x,r);	
		Client c3 = new Client (client3_x, r);
		Client c4 = new Client (client4_x, r);
		Client c5 = new Client (client5_x, r);
		Client c6 = new Client (client6_x, r);
		// On ajoute les clients � la liste.
		this.clients.add(c1);
		this.clients.add(c2);
		this.clients.add(c3);
		this.clients.add(c4);
		this.clients.add(c5);
		this.clients.add(c6);
		this.liste = liste;
	
	}
	
	/********************************************/
	/* M�thodes de la classe Ensclient */
	/********************************************/



	/**
	 * Méthode qui pour chaque client scanne puis réalise la commande.
	 * @throws IOException
	 */
	public void chacunSonTour (boolean moitie, SushiGoRound j) throws IOException{
		// Compteur qui permet de d�barrasser apr�s avoir servi deux clients
		int cpt = 0;
		// On parcourt tous les clients
		for (Client c : this.clients){
			cpt ++;
			// Pour chaque client on parcourt la liste des sushis du niveau en cours,
			// jusqu'� trouver le sushi qui correspond � la commande. Si aucun sushi ne correspond,
			// c'est qu'il n'y a pas de client ou que le client est entrain de manger.
			for (Sushi s : this.liste){
				if (c.getSushi() == null && s.reconnait(c)){
					// Si un des sushis correspond, on fait le sushi, et on sort de la boucle for
					c.setSushi(s);
					c.makeSushi(this,  moitie, j.getNiveau(),cpt);
					break;
				}
			} 
			// On d�barasse tous les deux clients parcourus.
			if (moitie) this.debarasseA();
			else if (cpt%2==0) this.debarasse(cpt);
			this.r.delay(this.delai_entre_client);
			/** Essayer debarasser C6 -> C1 **/
			/** PAS UTILISE **/
			// V�rifie si le jeu est proche de la fin 
			//j.checkTime();
		}
	}

	/** 
	 * Modifie la liste des sushi
	 * @param liste des sushi
	 */
	public void modListe (Liste_Sushi s){
		this.liste = s;
	}

	/**
	 * Méthode qui clique sur les assiettes de tous les clients
	 */
	public void debarasse (int cpt){
		if (cpt < 4){
		for (int i = cpt ; i < 6; i++){
			this.clients.get(i).clicAssiette();
		}
		}
		else {
			this.clients.get(4).clicAssiette();
				this.clients.get(5).clicAssiette();
			for (int i = 0 ; i < 4 ; i++){
				this.clients.get(i).clicAssiette();
			}
		}
	}
	
	public void debarasseA (){
		for (Client c : this.clients){
			c.clicAssiette();
		}
		
	}



}
