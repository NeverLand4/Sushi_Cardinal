package Ecran;

import java.awt.Robot;

public class Repere {
	private int coord_x, coord_y;
	
	public Repere (int x, int y ){
		this.coord_x = x;
		this.coord_y = y;
	}
	
	public int getCoord_x (){
		return this.coord_x;
	}
	
	public int getCoord_y (){
		return this.coord_y;
	}
	

}