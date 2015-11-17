package dots;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Scanner;

import core.*;

public class dotGame implements Game{

	GameEngineV2 engine;
	Board board;
	GameState GS;
	
	public static void main(String args[]) {
		new dotGame();
	}

	public dotGame() {
		int size;
		boolean valid = false;
		Scanner in = new Scanner(System.in);
		
		do{
			System.out.println("How long do you want the sides to be?(5, 7, 9): ");
			size = in.nextInt();
			if((size == 5) || (size == 7) || (size == 9)){
				valid = true;
			}
		}while(!valid);
		
		in.close();
		
		GS = GameState.SETUP;
		board = new Board(size);
		engine = new GameEngineV2(this);
		engine.setWindow("Dots for SS", board.getWidth(), board.getHeight(), 10);
		
		engine.start();
	}
	
	@Override
	public void processFrame() {
		switch (GS) {
		
		case SETUP:
			board.shuffle();
			GS = GameState.RUNNING;
			break;
			
		case RUNNING:
			if(engine.getKey(UP) == 1){
				engine.unflagKey(UP);
				board.moveUp();
			}
			if(engine.getKey(LEFT) == 1){
				engine.unflagKey(LEFT);
				board.moveLeft();
			}
			if(engine.getKey(RIGHT) == 1){
				engine.unflagKey(RIGHT);
				board.moveRight();
			}
			if(engine.getKey(DOWN) == 1){
				engine.unflagKey(DOWN);
				board.moveDown();
			}
			
			if(board.checkForWin()){
				GS = GameState.WIN;
			}
			
			if(engine.getKey((int)'n') == 1){
				engine.unflagKey((int)'n');
				GS = GameState.SETUP;
			}
			break;
			
		case WIN:
			if(engine.getKey((int)'n') == 1){
				engine.unflagKey((int)'n');
				GS = GameState.SETUP;
			}
			break;
			
		default:
			break;
		}	
		
	}

	@Override
	public void drawFrame(Graphics g) {
		switch (GS) {
		
		case SETUP:
			
			break;
			
		case RUNNING:
			board.draw(g);
			break;
			
		case WIN:
			g.setColor(Color.white);
			g.setFont(new Font("SansSerif", Font.BOLD, 80));
			g.drawString("You win!", 10, 10);
			
			g.setFont(new Font("SansSerif", Font.PLAIN, 60));
			g.drawString("Moves: " + board.getMoves(), 10, 30);
			
			g.drawString("Press 'n' for a new game", 10, 50);
			break;
			
		default:
			break;
		}	
	}

}
