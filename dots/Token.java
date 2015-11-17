package dots;

import java.awt.Color;
import java.awt.Graphics;

public class Token{
	
	public static final int TILESIZE = 60;
	
	  private Color color;
	  
	  public Token(Color color){
	    this.color = color;
	  }
	  
	  public void draw(Graphics g){
	    //draw the tile here
	  }
	  
	  public Color getColor(){
	    return color;
	  }
	  
}