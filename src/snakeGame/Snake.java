package snakeGame;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.Timer;

public class Snake implements ActionListener, KeyListener {

public JFrame snakeFrame;

public static Snake snake;

public GamePanel gamePanel;

public Timer timer = new Timer(20, this);

public ArrayList<Point> snakeList = new ArrayList<Point>();

public static final int UP =0, DOWN = 1, LEFT = 2, RIGHT = 3, snakeWidth = 10;

public int snakeDirection, ticks, gameScore, snakeLength;

public Dimension screenDimensions;

public Point snakeHead, snakeTargetPoint;

public Random random;

public boolean gameOver, gamePause;


public Snake() {
	screenDimensions = Toolkit.getDefaultToolkit().getScreenSize();
	
	snakeFrame = new JFrame("Snake");
	
	snakeFrame.setVisible(true);
	
	snakeFrame.setSize(600, 450);
	
	snakeFrame.setResizable(false);
	
	snakeFrame.setLocation(screenDimensions.width/2 -  snakeFrame.getWidth()/2, screenDimensions.height/2 - snakeFrame.getHeight()/2);

	snakeFrame.add(gamePanel = new GamePanel());
	
	snakeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	snakeFrame.addKeyListener(this);
	
	startGame();
}


public void startGame() {
	gameOver = false;
	gamePause = false;
	gameScore = 0;
	snakeLength =  1;
	snakeDirection = DOWN;
	snakeHead = new Point(0,3);
	random = new Random();
	snakeList.clear();
	snakeTargetPoint = new Point(random.nextInt(59), 3 + random.nextInt(37));
	
	timer.start();
	
}

public static void main(String[] args) {
	//new SoundClip();
	
	GameSound.MAINGAMESOUND.play();
	snake = new Snake();
}


public void actionPerformed(ActionEvent e) {
	
	gamePanel.repaint();
	ticks++;
	
	if(ticks % 3 ==0 && gameOver != true && gamePause != true) {
		
		if(snakeDirection == UP)
			if (snakeHead.y -1 >=3 && checkCollision(snakeHead.x, snakeHead.y-1))
				snakeHead  = new Point(snakeHead.x, snakeHead.y-1);
			else
			{ 
				GameSound.MAINGAMESOUND.stopIt();
			    gameOver = true;
			    GameSound.GAMEOVER.play();
		    }
		
		if(snakeDirection == DOWN )
			if (snakeHead.y + 1 < 41 && checkCollision(snakeHead.x, snakeHead.y+1))
				snakeHead  = new Point(snakeHead.x, snakeHead.y+1);
			else
			{ GameSound.MAINGAMESOUND.stopIt();
			gameOver = true;
			GameSound.GAMEOVER.play();
		}
		
		if(snakeDirection == LEFT)
			if (snakeHead.x -1 >=0 && checkCollision(snakeHead.x-1, snakeHead.y))
				snakeHead  = new Point(snakeHead.x-1, snakeHead.y);
			else{ GameSound.MAINGAMESOUND.stopIt();
			gameOver = true;
			GameSound.GAMEOVER.play();
		}
		
		
		if(snakeDirection == RIGHT)
			if (snakeHead.x +1 <59 && checkCollision(snakeHead.x+1, snakeHead.y))
				snakeHead  = new Point(snakeHead.x +1, snakeHead.y);
			else { GameSound.MAINGAMESOUND.stopIt();
				gameOver = true;
				GameSound.GAMEOVER.play();
			}
		
		
		snakeList.add(new Point(snakeHead.x, snakeHead.y));
		
		if(snakeList.size()> snakeLength)
			snakeList.remove(0); 
	
		
		if(snakeTargetPoint!= null) {
			if(snakeHead.equals(snakeTargetPoint)) {
				gameScore += 10;
				snakeLength++;
				snakeTargetPoint.setLocation(random.nextInt(59), 3 + random.nextInt(37));
				GameSound.MAINGAMESOUND.stopIt();
				GameSound.BEEP.play();
				GameSound.MAINGAMESOUND.play();
			}
			}
		}
	}

public boolean checkCollision(int x, int y) {
	for(Point point : snakeList) {
		if(point.equals(new Point(x,y)))
				return false;
	}
	return true;
}

@Override
public void keyPressed(KeyEvent e) {
	
	int i = e.getKeyCode();
	
	if( i  == KeyEvent.VK_LEFT && snakeDirection != RIGHT)
		snakeDirection = LEFT;
	if( i  == KeyEvent.VK_UP && snakeDirection != DOWN)
		snakeDirection = UP;
	if( i  == KeyEvent.VK_DOWN && snakeDirection != UP)
		snakeDirection = DOWN;
	if( i  == KeyEvent.VK_RIGHT && snakeDirection != LEFT)
		snakeDirection = RIGHT;
	
	if(i == KeyEvent.VK_ESCAPE && !gameOver) {
		   gamePause = !gamePause;
		   if(gamePause == true)
			   GameSound.MAINGAMESOUND.stopIt();
		   else
			   GameSound.MAINGAMESOUND.play();
	}
	
	if(i == KeyEvent.VK_SPACE && gameOver) {
		GameSound.GAMEOVER.stopIt();
		GameSound.MAINGAMESOUND.play();
		startGame();
	}
	
	
}

@Override
public void keyReleased(KeyEvent e) {
	// TODO Auto-generated method stub
	
}

@Override
public void keyTyped(KeyEvent e) {
	// TODO Auto-generated method stub
	
}

}



