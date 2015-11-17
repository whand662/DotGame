package dots;

import java.awt.Color;
import java.awt.Graphics;

public class Zone{
	  //for target zones
	public int fX, lX, fY, lY;
	private Color color;
	
	public Zone(Color color){
		this.color = color;
	}
	
	public Color getColor(){
	    return color;
	  }
}