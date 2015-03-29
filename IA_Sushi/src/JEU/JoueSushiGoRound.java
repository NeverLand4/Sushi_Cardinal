package JEU;

import java.awt.AWTException;
import java.awt.Robot;
import java.io.IOException;
import java.net.URISyntaxException;

import Ecran.Repere;
import Exception.JeuFiniException;

public class JoueSushiGoRound{
public static void main(String[] args) throws IOException, AWTException,
	URISyntaxException, JeuFiniException {

		// On crée un objet jeu.
		SushiGoRound jeu = new SushiGoRound(new Robot(), new Repere(0, 0));
		// On ouvre la fenêtre de jeu dans le navigateur par défaut
		jeu.ouvreFenetre();
		// Si on trouve
		if (jeu.trouveFenetre()){
		jeu.joue();
		}
		// Si on ne trouve pas la fenêtre on affiche le message suivant.
		else {
			System.out.println("Jeu pas trouvé");
		}
		/*Jeu jeu = new Jeu(new Robot(), new Repere(0, 0),550,550, "Cardinal.png");
		
		if (jeu.trouveFenetre()) {
		jeu.joueCardinal(); la fenêtre on joue
		if (jeu.trouveFenetre()) {
		 jeu.joue();
		
		}*/
		/*jeu.robotrep.getRobot().delay(3000);
		BufferedImage im = jeu.robotrep.getRobot().createScreenCapture(new Rectangle(425,
				696, 20 ,20));
		ImageIO.write(im, "png", new File ("Cardinal.png"));*/
		
	}
}