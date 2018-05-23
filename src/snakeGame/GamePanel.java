package snakeGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class GamePanel extends JPanel{
	
	public static final Color purple = new Color(9767071);
   
	@Override
public void paintComponent(Graphics graphics) {
	super.paintComponent(graphics);
	 
	graphics.setColor(purple);
	graphics.fillRect(2, 2, 590, 23);
	
	graphics.setColor(Color.DARK_GRAY);
	graphics.fillRect(2, 27, 590, 390);

	Snake snake = Snake.snake;
	
	graphics.setColor(Color.red);
	
	for(Point point: snake.snakeList) {
		
		graphics.fillRect(point.x*Snake.snakeWidth, point.y*Snake.snakeWidth, Snake.snakeWidth, Snake.snakeWidth);
	}
	
	graphics.fillRect(snake.snakeHead.x*Snake.snakeWidth, snake.snakeHead.y*Snake.snakeWidth, Snake.snakeWidth, Snake.snakeWidth);

	graphics.setColor(Color.WHITE);
	
	graphics.fillRect(snake.snakeTargetPoint.x*Snake.snakeWidth, snake.snakeTargetPoint.y*Snake.snakeWidth, Snake.snakeWidth, Snake.snakeWidth);

	String displayString = "Score: " + snake.gameScore;
	
	graphics.setColor(Color.white);
	graphics.setFont(new Font("TimesRoman", Font.PLAIN, 20)); 
	graphics.drawString(displayString, 5, 20);
	
    displayString = "Press ESC to Pause/Resume";
    graphics.drawString(displayString, (int)(getWidth()/2 + 60) , 20);
    
	graphics.setFont(new Font("TimesRoman", Font.BOLD, 23));
	displayString = "Game Over - Press spacebar to restart the game";
	
	
	if(snake.gameOver) {
		graphics.drawString(displayString, (int)(getWidth()/2 - displayString.length()*5),(int)snake.screenDimensions.getHeight()/5);
	}
		
	}
    	
}

