package Ecran;

import java.awt.Robot;
import java.awt.event.InputEvent;

public class Bouton {

	/********************************************/
	/* Attributs de la classe BOUTON */
	/********************************************/

	// Delai entre chaque clic
	private static final int DELAY_BTW_CLIC = 10;

	// Delai entre le moment o� l'on appuie et le moment o� l'on relache la
	// souris
	private static final int DELAY_PRESS_RELEASE = 110;

	/*
	 * Les attributs suivant sont protected pour que la classe Ingr�dient, qui
	 * h�rite de bouton puisse y acc�der.
	 */

	// Coordonn�es du bouton dans la fen�tre de jeu
	protected int x, y;

	// Robot qui permet de cliquer dans la fen�tre de jeu pour faire les
	// commandes.
	protected Robot robot;

	/*
	 * Repere qui repr�sente le coin gauche sup�rieur de la fen�tre de jeu, �
	 * partir du quel on calcule la position des diff�rents objets du jeu sur
	 * lesquels il faut cliquer dans la fen�tre enti�re
	 */
	protected Repere rep;

	/********************************************/
	/* Constructeur de la classe Bouton */
	/********************************************/

	public Bouton(int x, int y, RobotRepere r) {
		this.robot = r.getRobot();
		this.rep = r.getRepere();
		this.x = x + this.rep.getCoord_x();
		this.y = y + this.rep.getCoord_y();
	}

	
	/********************************************/
	/* M�thodes de la classe Bouton */
	/********************************************/

	
	/**
	 * M�thode qui clique sur le bouton
	 */
	public void clicGauche() {
		this.robot.delay(DELAY_BTW_CLIC);
		this.robot.mouseMove(this.x, this.y);
		this.robot.mousePress(InputEvent.BUTTON1_MASK);
		this.robot.delay(DELAY_PRESS_RELEASE);
		this.robot.mouseRelease(InputEvent.BUTTON1_MASK);
	}

	/**
	 * Méthode static qui clique à la coordonée (x,y)
	 * @param x
	 * @param y
	 * @param robot
	 */
	public static void clicGauche(int x, int y, Robot robot) {
		robot.delay(DELAY_BTW_CLIC);
		robot.mouseMove(x, y);
		robot.mousePress(InputEvent.BUTTON1_MASK);
		robot.delay(DELAY_PRESS_RELEASE);
		robot.mouseRelease(InputEvent.BUTTON1_MASK);
	}
	
	public void dragTO (int x, int y){
		this.robot.delay(110);
		this.robot.mouseMove(this.x, this.y);
		this.robot.delay(110);
		this.robot.mousePress(InputEvent.BUTTON1_MASK);
		this.robot.delay(110);
		this.robot.mouseMove(x+this.rep.getCoord_x(), y+this.rep.getCoord_y());
		this.robot.delay(110);
		this.robot.mouseRelease(InputEvent.BUTTON1_MASK);
	}

}