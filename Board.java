public class Zone(){
  //for target zones
  public void draw(Graphics g){
    //draw zone here
  }
}

public class Token(){
  private Color color;
  public void draw(Graphics g){
    //draw the tile here
  }
  public Color getColor(){
    return color;
  }
}

public class Board{

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
  
  public int getMoves(){
    return moves;
  }
  
  public void moveUp(){
    Token temp;
    if(eY > 0){
      temp = grid[eX][eY - 1];
      grid[eX][eY - 1] = grid[eX][eY];
      grid[eX][eY] = temp;
      eY--;
      moves++;
    }
  }
  
  public void moveDown(){
    Token temp;
    if(eY < (size - 1)){
      temp = grid[eX][eY + 1];
      grid[eX][eY + 1] = grid[eX][eY];
      grid[eX][eY] = temp;
      eY++;
      moves++;
    }
  }
  
  public void moveLeft(){
    Token temp;
    if(eX > 0){
      temp = grid[eX - 1][eY];
      grid[eX - 1][eY] = grid[eX][eY];
      grid[eX][eY] = temp;
      eX--;
      moves++;
    }
  }
  
  public void moveRight(){
    Token temp;
    if(eX < (size - 1)){
      temp = grid[eX + 1][eY];
      grid[eX + 1][eY] = grid[eX][eY];
      grid[eX][eY] = temp;
      eX++;
      moves++;
    }
  }
  
  public bool checkForWin(){
    //check individual zones for color correctness
    //if all is correct return true
  }
  
  public void destroyBoard(){
    //free grid from memory here
  }
}
