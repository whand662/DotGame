package dots;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Random;

import dots.Token;
import dots.Zone;

public class Board{

	public static final int HEADERSIZE = 30;
	private int size, moves;
	private int eX, eY;
	private Token[][] grid;
	private Zone[] zone;

	public Board(int size){
		if(size % 2 == 0){
			System.out.println("Invalid board size: " + size);
			System.exit(1);
		}
		moves = 0;
		this.size = size;
		eX = (size - 1) / 2;
    	eY = (size - 1) / 2;
    	grid = new Token[size][size];
    	zone = new Zone[4];
    	initZone();
	}
	
	private void initZone(){
		zone[0] = new Zone(Color.GREEN);
		zone[1] = new Zone(Color.BLUE);
		zone[2] = new Zone(Color.RED);
		zone[3] = new Zone(Color.YELLOW);
		
		zone[0].fX = 0;
		zone[0].lX = (size - 1) / 2;
		zone[0].fY = 0;
		zone[0].lY = ((size - 1) / 2) - 1;
		
		zone[1].fX = zone[0].lX + 1;
		zone[1].lX = size - 1;
		zone[1].fY = 0;
		zone[1].lY = (size - 1) / 2;
		
		zone[2].fX = zone[0].lY + 1;
		zone[2].lX = size - 1;
		zone[2].fY = zone[1].lY + 1;
		zone[2].lY = size - 1;
		
		zone[3].fX = 0;
		zone[3].lX = zone[0].lY;
		zone[3].fY = zone[0].lY + 1;
		zone[3].lY = size - 1;
	}
	
	public void draw(Graphics g){
		g.setColor(Color.DARK_GRAY);
		g.fillRect(0, 0, getWidth(), HEADERSIZE);
		g.setColor(Color.WHITE);
		g.setFont(new Font("SansSerif", Font.PLAIN, HEADERSIZE - 10));
		g.drawString("Moves: " + moves, 5, HEADERSIZE - 5);
		
		for(int k = 0; k < 4; k++){
			g.setColor(zone[k].getColor());
			g.fillRect(zone[k].fX * Token.TILESIZE, (zone[k].fY * Token.TILESIZE) + HEADERSIZE, (zone[k].lX - zone[k].fX + 1) * Token.TILESIZE, (zone[k].lY - zone[k].fY + 1) * Token.TILESIZE);
		}
		
		for(int i = 0; i < size; i++){
			for(int j = 0; j < size; j++){
				grid[j][i].draw(g, (j*Token.TILESIZE), (i*Token.TILESIZE) + HEADERSIZE);
			}
		}
	}
  
  public int getSize(){
	  return size;
  }
  
  public int getWidth(){
	  return size * Token.TILESIZE;
  }
  
  public int getHeight(){
	  return (size * Token.TILESIZE) + HEADERSIZE;
  }
  
  public void shuffle(){
	Random rand;
    int numR = 0, numY = 0, numB = 0, numG = 0;
    int random, max;
    
    rand = new Random();
    eX = (size - 1) / 2;
    eY = (size - 1) / 2;
    max = (eX * (eY + 1));
    
    for(int i = 0; i < size; i++){
      for(int j = 0; j < size; j++){
        if((i == eX) && (i == j)){
          grid[j][i] = new Token(Color.GRAY);
        }else{
          random = rand.nextInt(4); // make random = a random number0-3
          switch(random){
            case 0:
              if(numR < max){
                grid[j][i] = new Token(Color.RED);
                numR++;
              }else{
                j--;
              }
              break;
            case 1:
              if(numY < max){
                grid[j][i] = new Token(Color.YELLOW);
                numY++;
              }else{
                j--;
              }
              break;
            case 2:
              if(numB < max){
                grid[j][i] = new Token(Color.BLUE);
                numB++;
              }else{
                j--;
              }
              break;
            case 3:
              if(numG < max){
                grid[j][i] = new Token(Color.GREEN);
                numG++;
              }else{
                j--;
              }
              break;
          }
        }
      }
    }
    
  }
  
  public int getMoves(){
    return moves;
  }
  
  public void moveDown(){
    Token temp;
    if(eY > 0){
      if(checkMoveDown(eX, eY, grid[eX][eY - 1].getColor())){
        temp = grid[eX][eY - 1];
        grid[eX][eY - 1] = grid[eX][eY];
        grid[eX][eY] = temp;
        eY--;
        moves++;
      }
    }
  }
  
  public void moveUp(){
    Token temp;
    if(eY < (size - 1)){
      if(checkMoveUp(eX, eY, grid[eX][eY + 1].getColor())){
        temp = grid[eX][eY + 1];
        grid[eX][eY + 1] = grid[eX][eY];
        grid[eX][eY] = temp;
        eY++;
        moves++;
      }
    }
  }
  
  public void moveRight(){
    Token temp;
    if(eX > 0){
      if(checkMoveRight(eX, eY, grid[eX - 1][eY].getColor())){
        temp = grid[eX - 1][eY];
        grid[eX - 1][eY] = grid[eX][eY];
        grid[eX][eY] = temp;
        eX--;
      moves++;
      }
    }
  }
  
  public void moveLeft(){
    Token temp;
    if(eX < (size - 1)){
      if(checkMoveLeft(eX, eY, grid[eX + 1][eY].getColor())){
        temp = grid[eX + 1][eY];
        grid[eX + 1][eY] = grid[eX][eY];
        grid[eX][eY] = temp;
        eX++;
        moves++;
      }
    }
  }
  
  public boolean checkForWin(){
    for(int i = 0; i <= 3; i++){
    	
    	for(int j = zone[i].fY; j <= zone[i].lY; j++){
    		
    		for(int k = zone[i].fX; k <= zone[i].lX; k++){
        		
        		if(grid[k][j].getColor() != zone[i].getColor()){
        			return false;
        		}
        		
        	}
    		
    	}
    	
    }
	  
	  return true;
  }

  public void destroyBoard(){
    //free grid from memory here
  }
  
  public boolean checkMoveUp(int x, int y, Color color){
	    if(x < size - 1){
	    	if(grid[x + 1][y].getColor() == color){
	    		return true;
	    	}
	    }
	    
	    if(x >= 1){
	    	if(grid[x - 1][y].getColor() == color){
	    		return true;
	    	}
	    }
	    
	    if(y >= 1){
	    	if(grid[x][y - 1].getColor() == color){
	    		return true;
	    	}
	    }  
	    
	    if(y < size - 2){
	    	if(grid[x][y + 2].getColor() == color){
	    		return true;
	    	}
	    }
	    
	    if(y < size - 1 && x < size - 1){
	    	if(grid[x + 1][y + 1].getColor() == color){
	    		return true;
	    	}
	    }
	    if(y < size - 1 && x >= 1){
	    	if(grid[x - 1][y + 1].getColor() == color){
	    		return true;
	    	}
	    }
	    
	    return false;
	  }
  
  public boolean checkMoveDown(int x, int y, Color color){
	    if(x < size - 1){
	    	if(grid[x + 1][y].getColor() == color){
	    		return true;
	    	}
	    }
	    
	    if(x >= 1){
	    	if(grid[x - 1][y].getColor() == color){
	    		return true;
	    	}
	    }
	    
	    if(y < size - 1){
	    	if(grid[x][y + 1].getColor() == color){
	    		return true;
	    	}
	    }  
	    
	    if(y >= 2){
	    	if(grid[x][y - 2].getColor() == color){
	    		return true;
	    	}
	    }
	    
	    if(y >= 1 && x < size - 1){
	    	if(grid[x + 1][y - 1].getColor() == color){
	    		return true;
	    	}
	    }
	    if(y >= 1 && x >= 1){
	    	if(grid[x - 1][y - 1].getColor() == color){
	    		return true;
	    	}
	    }
	    
	    return false;
	  }
  
  public boolean checkMoveLeft(int x, int y, Color color){
	    if(y < size - 1){
	    	if(grid[x][y + 1].getColor() == color){
	    		return true;
	    	}
	    }
	    
	    if(y >= 1){
	    	if(grid[x][y - 1].getColor() == color){
	    		return true;
	    	}
	    }
	    
	    if(x >= 1){
	    	if(grid[x - 1][y].getColor() == color){
	    		return true;
	    	}
	    }  
	    
	    if(x < size - 2){
	    	if(grid[x + 2][y].getColor() == color){
	    		return true;
	    	}
	    }
	    
	    if(x < size - 1 && y < size - 1){
	    	if(grid[x + 1][y + 1].getColor() == color){
	    		return true;
	    	}
	    }
	    if(x < size - 1 && y >= 1){
	    	if(grid[x + 1][y - 1].getColor() == color){
	    		return true;
	    	}
	    }
	    
	    return false;
	  }
  
  public boolean checkMoveRight(int x, int y, Color color){
	  if(y < size - 1){
	    	if(grid[x][y + 1].getColor() == color){
	    		return true;
	    	}
	    }
	    
	    if(y >= 1){
	    	if(grid[x][y - 1].getColor() == color){
	    		return true;
	    	}
	    }
	    
	    if(x < size - 1){
	    	if(grid[x + 1][y].getColor() == color){
	    		return true;
	    	}
	    }  
	    
	    if(x >= 2){
	    	if(grid[x - 2][y].getColor() == color){
	    		return true;
	    	}
	    }
	    
	    if(x >= 1 && y < size - 1){
	    	if(grid[x - 1][y + 1].getColor() == color){
	    		return true;
	    	}
	    }
	    if(x >= 1 && y >= 1){
	    	if(grid[x - 1][y - 1].getColor() == color){
	    		return true;
	    	}
	    }
	    
	    return false;
	  }
}
