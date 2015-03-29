package JEU;

import java.awt.AWTException;
import java.awt.Robot;
import java.io.IOException;
import java.net.URISyntaxException;

import Ecran.Repere;

public class JoueCardinal{
	
	public static void main (String [] args) throws IOException, URISyntaxException, AWTException{

		Cardinal jeu = new Cardinal(new Robot(), new Repere(0, 0),550,550, "Cardinal.png");
		jeu.ouvreFenetre();
		if (jeu.trouveFenetre()) {
		jeu.joue();
		}
		/*jeu.robotrep.getRobot().delay(3000);
		BufferedImage im = jeu.robotrep.getRobot().createScreenCapture(new Rectangle(425,
				696, 20 ,20));
		ImageIO.write(im, "png", new File ("Cardinal.png"));*/
	}
}