package Ecran;
import java.awt.Robot;

public class RobotRepere {
	private Robot robot;
	private Repere repere;
	
	public RobotRepere(Robot r, Repere rep){
		robot = r;
		repere = rep;
	}
	
	public Robot getRobot(){
		return robot;
	}
	
	public Repere getRepere(){
		return repere;
	}
	
	public void setRepere (Repere rep){
		this.repere = rep;
	}
}
