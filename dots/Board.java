package dots;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import dots.Token;
import dots.Zone;

public class Board{

	public static final int HEADERSIZE = 30;
	private int size, moves;
	private int eX, eY;
	private Token[][] grid;

	public Board(int size){
		moves = 0;
		this.size = size;
		eX = (size - 1) / 2;
    	eY = (size - 1) / 2;
    	grid = new Token[size][size];
	}
	
	public void draw(Graphics g){
		
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
      if(checkMoveLoc(eX, eY - 1, grid[eX][eY - 1].getColor()) && checkMoveLoc(eX, eY, grid[eX][eY - 1].getColor())){
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
      if(checkMoveLoc(eX, eY + 1, grid[eX][eY + 1].getColor()) && checkMoveLoc(eX, eY, grid[eX][eY + 1].getColor())){
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
      if(checkMoveLoc(eX - 1, eY, grid[eX - 1][eY].getColor()) && checkMoveLoc(eX, eY, grid[eX - 1][eY].getColor())){
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
      if(checkMoveLoc(eX + 1, eY, grid[eX + 1][eY].getColor()) && checkMoveLoc(eX, eY, grid[eX + 1][eY].getColor())){
        temp = grid[eX + 1][eY];
        grid[eX + 1][eY] = grid[eX][eY];
        grid[eX][eY] = temp;
        eX++;
        moves++;
      }
    }
  }
  
  public boolean checkMoveLoc(int x, int y, Color color){
    if(grid[x + 1][y].getColor() == color){
      return true;
    }else if(grid[x - 1][y].getColor() == color){
      return true;
    }else if(grid[x][y + 1].getColor() == color){
      return true;
    }else if(grid[x][y - 1].getColor() == color){
      return true;
    }
    
    return false;
  }
  
  public boolean checkForWin(){
    //check individual zones for color correctness
    //if all is correct return true
	  
	  return false;
  }

  public void destroyBoard(){
    //free grid from memory here
  }
}
