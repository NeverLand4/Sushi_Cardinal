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
		/* M�thodes de la classe NIVEAU7 */
	/********************************************/
	/** 
	 * Méthode qui arr�te l'ex�cution du jeu
	 * @return variable de type Niveau7 qui contient les nouveaux sushis + ingrédients
	 * @throws IOException, JeuFiniException
	 */
	public Niveau7 nextLevel() throws JeuFiniException, IOException{
		boolean test = true;
		if (test) throw new JeuFiniException();
		return new Niveau7 (super.r);
	}
}
