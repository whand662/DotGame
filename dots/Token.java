package dots;

import java.awt.Color;
import java.awt.Graphics;

public class Token{
	
	public static final int TILESIZE = 60;
	
	  private Color color;
	  
	  public Token(Color color){
	    this.color = color;
	  }
	  
	  public void draw(Graphics g, int x, int y){
	    if(color != Color.GRAY){
		  g.setColor(color);
		  g.fillOval(x + 5, y + 5, TILESIZE - 10, TILESIZE - 10);
	    }
	  }
	  
	  public Color getColor(){
	    return color;
	  }
	  
}