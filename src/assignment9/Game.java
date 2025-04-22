package assignment9;

import java.awt.Color;
import java.awt.event.KeyEvent;

import edu.princeton.cs.introcs.StdDraw;

public class Game {
	
	private Snake mySnake; 
	private Food myFood; 
	private int score; 
	
	public Game() {
		StdDraw.enableDoubleBuffering();
		
		//FIXME - construct new Snake and Food objects
		mySnake = new Snake();
		myFood = new Food();
		score = 0; 
	}
	
	public void play() {
		int currentDirection = 0;
		while (mySnake.isInbounds()) { //TODO: Update this condition to check if snake is in bounds
			int dir = getKeypress();
			//Testing only: you will eventually need to do more work here
			System.out.println("Keypress: " + dir);
			
			/*
			 * 1. Pass direction to your snake
			 * 2. Tell the snake to move
			 * 3. If the food has been eaten, make a new one
			 * 4. Update the drawing
			 */
			
			if (!((dir == 1 && currentDirection == 2) || (dir == 2 && currentDirection == 1) || (dir == 3 && currentDirection == 4) || (dir == 4 && currentDirection == 3))){
				mySnake.changeDirection(dir);
			}
			
			mySnake.move();
			if (mySnake.eatFood(myFood)) {
				myFood = new Food();
				myFood.draw();
				score++; 
			}
			updateDrawing();
			currentDirection = dir; 
		}
		
		System.out.println("Game over!");
	}
	
	private int getKeypress() {
		if(StdDraw.isKeyPressed(KeyEvent.VK_W)) {
			return 1;
		} else if (StdDraw.isKeyPressed(KeyEvent.VK_S)) {
			return 2;
		} else if (StdDraw.isKeyPressed(KeyEvent.VK_A)) {
			return 3;
		} else if (StdDraw.isKeyPressed(KeyEvent.VK_D)) {
			return 4;
		} else {
			return -1;
		}
	}
	
	/**
	 * Clears the screen, draws the snake and food, pauses, and shows the content
	 */
	private void updateDrawing() {
		//FIXME
		
		/*
		 * 1. Clear screen
		 * 2. Draw snake and food
		 * 3. Pause (50 ms is good)
		 * 4. Show
		 */
		
		StdDraw.clear();
		mySnake.draw();
		myFood.draw();
		
		StdDraw.setPenColor(Color.BLACK);
	    StdDraw.text(0.5, 0.95, "Score: " + score);
	    
	    
		StdDraw.pause(50);
		StdDraw.show(); 
	}
	
	public static void main(String[] args) {
		Game g = new Game();
		g.play();
	}
}
