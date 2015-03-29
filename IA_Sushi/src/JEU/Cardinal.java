package JEU;

import java.awt.AWTException;
import java.awt.Desktop;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import Ecran.Bouton;
import Ecran.Image;
import Ecran.Repere;
import Ecran.RobotRepere;

public class Cardinal extends SushiGoRound{
	
	
	public Cardinal(Robot r, Repere rep, int dim_x, int dim_y, String coinJeu)throws IOException, URISyntaxException {
		super(r, rep);
			robotrep = new RobotRepere(r, rep);		
			screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			this.coinJeu = Image.readIm(coinJeu);
			this.dim_jeu_x = dim_x;
			this.dim_jeu_y = dim_y;
			taille = 0;	

	}

	public void ouvreFenetre() throws IOException, URISyntaxException {
		Desktop.getDesktop()
		.browse(new URI(
				"http://www.newgrounds.com/portal/view/634256"));
		this.robotrep.getRobot().delay(5000);
		this.robotrep.getRobot().mouseWheel(3);
	}



	public void commenceJeu() throws IOException{
		Bouton play = new Bouton(270,522,this.robotrep);
		play.clicGauche();
		// 
		/*for (int i = 0 ; i < 20 ; i++){
			ImageIO.write(this.robotrep.getRobot().createScreenCapture(new Rectangle(this.robotrep.getRepere().getCoord_x(),
					this.robotrep.getRepere().getCoord_y(), 550 ,550)),
					"png", new File ("Cardinal"+i+".png"));
			this.robotrep.getRobot().delay(2000);
		}*/
		/*this.robotrep.getRobot().delay(5000);
		BufferedImage im = this.robotrep.getRobot().createScreenCapture(new Rectangle(this.robotrep.getRepere().getCoord_x()+700,
				this.robotrep.getRepere().getCoord_y()+250, 1 ,1));
		System.out.println(im.getRGB(0, 0));*/
		
	}
	
	public void joue() throws IOException{
		//System.out.println(this.robotrep.getRepere().getCoord_x()+" "+this.robotrep.getRepere().getCoord_y());
		this.robotrep.setRepere(new Repere (this.robotrep.getRepere().getCoord_x(), this.robotrep.getRepere().getCoord_y()-530));
		Bouton play = new Bouton(270,522,this.robotrep);
		play.clicGauche();
		this.robotrep.getRobot().delay(1500);
		//int a = 0;
		for (int i = 0 ; i < 100; i ++){
		BufferedImage im = this.robotrep.getRobot().createScreenCapture(new Rectangle(this.robotrep.getRepere().getCoord_x(),
				this.robotrep.getRepere().getCoord_y(), 550 ,550));
		/*ImageIO.write(im,"png", new File( "test"+i+".png"));*/
		System.out.println(im.getRGB(270,90)+" "+im.getRGB(270,460)+" "+im.getRGB(90,270)+" "+im.getRGB(460,270));
		
		 if (im.getRGB(270,90)!=-5242598){
			
			 System.out.println("up");
			this.robotrep.getRobot().keyPress(KeyEvent.VK_UP);
			//this.robotrep.getRobot().delay(500);
			this.robotrep.getRobot().keyRelease(KeyEvent.VK_UP);
		
			
		}
		 else if ( im.getRGB(270,460)!=-5242598){
			 System.out.println("down");
			 this.robotrep.getRobot().keyPress(KeyEvent.VK_DOWN);
				//this.robotrep.getRobot().delay(500);
				this.robotrep.getRobot().keyRelease(KeyEvent.VK_DOWN);
		
		}
		 else if (im.getRGB(90,270)!=-5242598) {
			 System.out.println("lfeft");
			 this.robotrep.getRobot().keyPress(KeyEvent.VK_LEFT);
				//this.robotrep.getRobot().delay(500);
				this.robotrep.getRobot().keyRelease(KeyEvent.VK_LEFT);
			
		}
		 else if (im.getRGB(460,270)!=-5242598 ){
			
			 System.out.println("right");
			 this.robotrep.getRobot().keyPress(KeyEvent.VK_RIGHT);
			//this.robotrep.getRobot().delay(500);
			this.robotrep.getRobot().keyRelease(KeyEvent.VK_RIGHT);
		
		}
		 System.out.println(i);
			this.robotrep.getRobot().delay(490);
		}
		
	}
	

}