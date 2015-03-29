package Niveaux;

import java.io.IOException;

import Ecran.RobotRepere;
import Exception.JeuFiniException;

public class Niveau7 extends Niveau6{

	/********************************************/
		/* Constructeur de la classe NIVEAU7 */
	/********************************************/
	public Niveau7 (RobotRepere r) throws IOException{
		super (r);
	}


	/********************************************/
		/* Méthodes de la classe NIVEAU7 */
	/********************************************/
	/** 
	 * MÃ©thode qui arrête l'exécution du jeu
	 * @return variable de type Niveau7 qui contient les nouveaux sushis + ingrÃ©dients
	 * @throws IOException, JeuFiniException
	 */
	public Niveau7 nextLevel() throws JeuFiniException, IOException{
		boolean test = true;
		if (test) throw new JeuFiniException();
		return new Niveau7 (super.r);
	}
}
