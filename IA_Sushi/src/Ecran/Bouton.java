package Ecran;

import java.awt.Robot;
import java.awt.event.InputEvent;

public class Bouton {

	/********************************************/
	/* Attributs de la classe BOUTON */
	/********************************************/

	// Delai entre chaque clic
	private static final int DELAY_BTW_CLIC = 10;

	// Delai entre le moment où l'on appuie et le moment où l'on relache la
	// souris
	private static final int DELAY_PRESS_RELEASE = 110;

	/*
	 * Les attributs suivant sont protected pour que la classe Ingrédient, qui
	 * hérite de bouton puisse y accéder.
	 */

	// Coordonnées du bouton dans la fenêtre de jeu
	protected int x, y;

	// Robot qui permet de cliquer dans la fenêtre de jeu pour faire les
	// commandes.
	protected Robot robot;

	/*
	 * Repere qui représente le coin gauche supérieur de la fenêtre de jeu, à
	 * partir du quel on calcule la position des différents objets du jeu sur
	 * lesquels il faut cliquer dans la fenêtre entière
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
	/* Méthodes de la classe Bouton */
	/********************************************/

	
	/**
	 * Méthode qui clique sur le bouton
	 */
	public void clicGauche() {
		this.robot.delay(DELAY_BTW_CLIC);
		this.robot.mouseMove(this.x, this.y);
		this.robot.mousePress(InputEvent.BUTTON1_MASK);
		this.robot.delay(DELAY_PRESS_RELEASE);
		this.robot.mouseRelease(InputEvent.BUTTON1_MASK);
	}

	/**
	 * MÃ©thode static qui clique Ã  la coordonÃ©e (x,y)
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

}