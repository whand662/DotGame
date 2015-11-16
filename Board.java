public class Zone(){
  //for target zones
  public void draw(Graphics g){
    //draw zone here
  }
}

public class Token(){
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
  
  public void shuffle(){
    int numR = 0, numY = 0, numB = 0, numG = 0;
    int random, max;
    eX = (size - 1) / 2;
    eY = (size - 1) / 2;
    max = (eX * (eY + 1));
    
    for(int i = 0; i < size; i++){
      for(int j = 0; j < size; j++){
        if((i == eX) && (i == j)){
          grid[j][i] = Token(Color(GREY));
        }else{
          random = rand(0, 3) // make random = a random number0-3
          switch(random){
            case 0:
              if(numR < max){
                grid[j][i] = Token(Color(RED));
                numR++;
              }else{
                j--;
              }
              break;
            case 1:
              if(numY < max){
                grid[j][i] = Token(Color(YELLOW));
                numY++;
              }else{
                j--;
              }
              break;
            case 2:
              if(numB < max){
                grid[j][i] = Token(Color(BLUE));
                numB++;
              }else{
                j--;
              }
              break;
            case 3:
              if(numG < max){
                grid[j][i] = Token(Color(GREEN));
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
  
  public bool checkMoveLoc(int x, int y, Color color){
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
  
  public bool checkForWin(){
    //check individual zones for color correctness
    //if all is correct return true
  }
  
  public void destroyBoard(){
    //free grid from memory here
  }
}
