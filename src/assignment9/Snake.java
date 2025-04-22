package assignment9;

import java.util.LinkedList;

public class Snake {

	private static final double SEGMENT_SIZE = 0.02;
	private static final double MOVEMENT_SIZE = SEGMENT_SIZE * 1.5;
	private LinkedList<BodySegment> segments;
	private double deltaX;
	private double deltaY;
	private int currentDirection; 
	
	public Snake() {
		BodySegment next = new BodySegment(0.5, 0.5, SEGMENT_SIZE); 
		this.segments = new LinkedList<BodySegment>();
		segments.add(next);
		deltaX = 0.0;
		deltaY = 0.0;
	}
	
	public void changeDirection(int direction) {
	
		
		if(direction == 1) { //up
			deltaY = MOVEMENT_SIZE;
			deltaX = 0;
		} else if (direction == 2) { //down
			deltaY = -MOVEMENT_SIZE;
			deltaX = 0;
		} else if (direction == 3) { //left
			deltaY = 0;
			deltaX = -MOVEMENT_SIZE;
		} else if (direction == 4) { //right
			deltaY = 0;
			deltaX = MOVEMENT_SIZE;
		}
	}
	
	/**
	 * Moves the snake by updating the position of each of the segments
	 * based on the current direction of travel
	 */
	public void move() {
		double newX = segments.get(0).getX() + deltaX;
        double newY = segments.get(0).getY() + deltaY;

        BodySegment newHead = new BodySegment(newX, newY, SEGMENT_SIZE);
        segments.addFirst(newHead);
        
        segments.removeLast();
        
  
		
	}
	
	/**
	 * Draws the snake by drawing each segment
	 */
	public void draw() {
		for(int i = 0; i < segments.size(); i++) {
			BodySegment draw = segments.get(i);
			draw.draw();
		}
	}
	
	/**
	 * The snake attempts to eat the given food, growing if it does so successfully
	 * @param f the food to be eaten
	 * @return true if the snake successfully ate the food
	 */
	public boolean eatFood(Food f) {
		BodySegment head = segments.get(0);
		double dist = Math.sqrt(Math.pow(head.getX() - f.getX(), 2) + Math.pow(head.getY() - f.getY(), 2));

        if (dist < SEGMENT_SIZE + Food.FOOD_SIZE) {
            BodySegment newSeg = new BodySegment(head.getX(), head.getY(), SEGMENT_SIZE);
            segments.addFirst(newSeg);
            return true;
        }
        return false;
	}
	
	/**
	 * Returns true if the head of the snake is in bounds
	 * @return whether or not the head is in the bounds of the window
	 */
	public boolean isInbounds() {
		if ((segments.get(0).getX() < 1 && segments.get(0).getX() > 0) && (segments.get(0).getY() < 1 && segments.get(0).getY() > 0)) {
			return true; 
		}
		return false;
	}
}
