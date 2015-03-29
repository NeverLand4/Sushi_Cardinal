package Niveaux;

import java.io.IOException;

import Ecran.RobotRepere;
import Exception.JeuFiniException;
import Sushi.Recette;
import Sushi.Sushi;

public class Niveau5 extends Niveau4 {

	/********************************************/
		/* Attributs de la classe NIVEAU5 */
	/********************************************/
	
	// Recette du nouveau sushi
	protected Recette rec_Dragon = new Recette(rice, nori, rice, roe, unagi,
			unagi);
	
	// Nouveau sushi
	protected Sushi Dragon_Roll = new Sushi("Dragon_Roll", rec_Dragon, tapis_prep);

	

	/********************************************/
		/* Constructeur de la classe NIVEAU5 */
	/********************************************/
	public Niveau5 (RobotRepere r) throws IOException{
		super (r);
		rec_Dragon = new Recette(rice, nori, rice, roe, unagi,
				unagi);
		Dragon_Roll = new Sushi("Dragon_Roll", rec_Dragon, tapis_prep);
		super.liste_sushi.add(Dragon_Roll);
		}
	
	

	/********************************************/
		/* M�thodes de la classe NIVEAU5 */
	/********************************************/
	/** 
	 * Méthode qui passse au niveau suivant
	 * @return variable de type Niveau5 qui contient les nouveaux sushis + ingrédients
	 * @throws IOException
	 */
	public Niveau6 nextLevel() throws JeuFiniException, IOException{
		System.out.println("Niveau6");
		return new Niveau6 (super.r);
	}
}
