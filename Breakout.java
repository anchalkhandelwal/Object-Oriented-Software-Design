import java.awt.Button;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;
import java.util.Queue;
import java.util.Stack;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import org.apache.log4j.Logger;

public class Breakout extends JPanel implements ActionListener {

	public static Graphics2D g2d = null;
	static boolean gameIsOn = true;
	static int win = 0;
	static int StartGame = 0;
	
	//Adding logger
	final static Logger logger = Logger.getLogger(Breakout.class);
	Ball ball;
	Paddle paddle;
	Brick brick;
	Clock clock;
	JButton replay, undo, pause, start;
	
	//Stack Objects
	static Stack<Ball> ballObjects;
	static Stack<Paddle> paddleObjects;
	static Stack<Clock> clockObjects;
	static Stack<Brick> brickObjects;
	
	//Queue objects
	static ArrayList<Ball> ballQueue;
	static ArrayList<Brick> brickQueue;
	static ArrayList<Clock> clockQueue;
	static ArrayList<Paddle> paddleQueue;
	
	//loop counter for replay
	static int counter;
	static int breakLoop = 1; 
	int play = 0, pauseChecker = 0, startChecker = 0, undoCheck = 0, replayCheck = 0;
	Clock tempClock = new Clock();
	BreakoutObservable observable;
	
	Breakout()
	{
				
	}
	Breakout(Ball ball, Paddle paddle, Brick brick, Clock clock)
	{
		this.ball = ball;
		this.paddle = paddle;
		this.brick = brick;
		this.clock = clock;
		this.addKeyListener(paddle);
		setFocusable(true);
		replay = new JButton("Replay");
		replay.setFocusable(false);
		undo = new JButton("undo");
		undo.setFocusable(false);
		pause = new JButton("Pause");
		pause.setFocusable(false);
		start = new JButton("Start");
		start.setFocusable(false);
		pause.addActionListener(this);
		start.addActionListener(this);
		undo.addActionListener(this);
		replay.addActionListener(this);
		this.add(replay);
		this.add(undo);
		this.add(pause);
		this.add(start);
		ballObjects = new Stack<Ball>();
		paddleObjects = new Stack<Paddle>();
		clockObjects = new Stack<Clock>();
		brickObjects = new Stack<Brick>();
		ballQueue = new ArrayList<Ball>();
		brickQueue = new ArrayList<Brick>();
		clockQueue = new ArrayList<Clock>();
		paddleQueue = new ArrayList<Paddle>();
		
		this.ball.registerBall();
		this.clock.registerClock();
		
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g2d = (Graphics2D) g;
		//drawing paddle
		paddle.draw(g2d);	
		//drawing ball
		ball.draw(g2d);
		
		g.setFont(new Font("TimesRoman", Font.BOLD, 12));
		clock.draw(g2d);
		
		
		g2d.setColor(Color.ORANGE);
		brick.draw(g2d);
		
		if(!gameIsOn && win != 1 && replayCheck != 1)
		{
			g2d.setColor(Color.RED);
			g2d.drawString("GAME OVER!", Constants.GAMEOVER_POS_X, Constants.GAMEOVER_POS_Y);
		}
		if(win == 1)
		{
			g2d.setColor(Color.RED);
			g2d.drawString("You are Victorious!", Constants.WIN_POS_X, Constants.WIN_POS_Y);
		}
		if(StartGame == 1)
		{
			g2d.setColor(Color.RED);
			g2d.drawString("Press Restart or Resume to Continue", Constants.WIN_POS_X-50, Constants.WIN_POS_Y-50);
		}
	}
	
	public void startGame()
	{				
		while(true)
		{ System.out.print("");
		if(replayCheck == 1)
		{	
			int gameIsOnFlag = 0, gameOverFlag = 0;
			clock.pauseFlag = 1;
			for(int i = 0; i < ballQueue.size(); i++){
				counter =i;
				try {
					Thread.sleep(50);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				BallCommands ballCommands = new BallCommands(ball);
				PaddleCommands paddleCommands = new PaddleCommands(paddle);
				BrickCommands brickCommands = new BrickCommands(brick);
				ClockCommands clockCommands = new ClockCommands(clock);
				
				ball = (Ball) ballCommands.replay();
				paddle = (Paddle) paddleCommands.replay();
				brick = (Brick) brickCommands.replay();
				tempClock = (Clock) clockCommands.replay();
				this.clock.clockMinutes = tempClock.clockMinutes;
				this.clock.clockSeconds = tempClock.clockSeconds;
				this.clock.clockMiliSecs = tempClock.clockMiliSecs;

				repaint();	
			}
		
			breakLoop = 1;
			StartGame = 1;
			this.addKeyListener(paddle);
			if(gameIsOnFlag == 1 && gameOverFlag == 0)
			{
				gameIsOn = false;
				repaint();
			}
			replayCheck = 0;
			clock.pauseFlag = 0;
		}


		
			if(breakLoop == 0)
			{
				StartGame = 0;
				while(gameIsOn)
				{
					if(breakLoop == 1)
					{
						break;	// break the while(gameIsOn) loop?
					}
					ball.moveBall();
			
					brick.brickCollide(ball);
					storeInstance(ball, paddle, clock, brick);	
					observable = new BreakoutObservable(paddle, ball);
					observable.notifyObservers();
			
					if(checkWin(brick))
					{
						gameIsOn = false;
						win = 1;
						breakLoop = 1;
					}
			
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					repaint();

				}
			}


			if(breakLoop != 1)
			{
				ball.unregisterBall();
				clock.unregisterClock();
			}
		}
		
		
	}
	
	

	public boolean checkWin(Brick brick)
	{
		for(int i = 0; i < Constants.BRICK_ROW; i++)
		{
			for(int j = 0; j < Constants.BRICK_COLUMN; j++)
			{
				if(brick.getBricksX()[i][j] != -1)
				{
					return false;
				}
			}
		}
		return true;
	}
	
	
	public void storeInstance(Ball ball, Paddle paddle, Clock clock, Brick brick)
	{
		Ball cloneBall = null;
		Paddle clonePaddle = null;
		Brick cloneBrick = null;
		Clock cloneClock = null;
		try { 
			cloneBall = (Ball) ball.clone();
			clonePaddle = (Paddle) paddle.clone();
			cloneBrick = (Brick) brick.copy(brick);
			cloneClock = (Clock) clock.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ballObjects.push(cloneBall);
		paddleObjects.push(clonePaddle);
		clockObjects.push(cloneClock);
		brickObjects.push(cloneBrick);
		
		ballQueue.add(cloneBall);
		brickQueue.add(cloneBrick);
		clockQueue.add(cloneClock);
		paddleQueue.add(clonePaddle);
	}
		
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if (e.getSource() == pause) {
			if (undoCheck == 1) {
				logger.debug("Game Resumed");
				breakLoop = 0;
				clock.pauseFlag = 0;
				pause.setText("pause");
				undoCheck = 0;
			} else {
				logger.debug("Game Resumed");
				pauseChecker++;
				if (pauseChecker % 2 == 0) {
					breakLoop = 0;
					pause.setText("Pause");
					clock.pauseFlag = 0;
				} else {
					logger.debug("Game Paused");
					breakLoop = 1;
					pause.setText("Resume");
					clock.pauseFlag = 1;
				}
			}
		}
		
		else if(e.getSource() == start)
		{	
			logger.debug("Game Started");
			startChecker++;
			if(startChecker == 1)
			{
				breakLoop = 0;
				start.setText("Restart");
			}
			else
			{
				if(replayCheck == 1){logger.debug("Cannot Restart while Replay is ON");}
				else{
					logger.debug("Game Restarted");
					StartGame = 0;
					win = 0;
					undoCheck = 0;
					pause.setText("pause");
					observable.deleteObservers();
					ball.setBx(Constants.BALL_POS_X);
					ball.setBy(Constants.BALL_POS_Y);
					ball.setMoveX(Constants.BALL_VEL_X);
					ball.setMoveY(Constants.BALL_VEL_Y);
					paddle.setPx(Constants.PADDLE_POS_X);
					paddle.setPy(Constants.PADDLE_POS_Y);
					this.brick = new Brick(1);
					this.clock = new Clock();
					ball.registerBall();
					clock.registerClock();
					breakLoop = 0;
					gameIsOn = true;
					brickObjects.removeAllElements();
					paddleObjects.removeAllElements();
					ballObjects.removeAllElements();
					clockObjects.removeAllElements();
					//set initial values then startGame()
					
					ballQueue.removeAll(ballQueue);
					clockQueue.removeAll(clockQueue);
					paddleQueue.removeAll(paddleQueue);
					brickQueue.removeAll(brickQueue);
				}
			}
		}
		
		else if(e.getSource() == undo)
		{			
			if(replayCheck == 1){
				logger.debug("Undo is Clicked but Replay is ON");
			}
			else{
				logger.debug("Undo is Processed");
			breakLoop = 1;
			BallCommands ballCommands = new BallCommands(ball);
			PaddleCommands paddleCommands = new PaddleCommands(paddle);
			BrickCommands brickCommands = new BrickCommands(brick);
			ClockCommands clockCommands = new ClockCommands(clock);
			
			ball = (Ball) ballCommands.undo();
			paddle = (Paddle) paddleCommands.undo();
			brick = (Brick) brickCommands.undo();
			tempClock = (Clock) clockCommands.undo();
			this.clock.clockMinutes = tempClock.clockMinutes;
			this.clock.clockSeconds = tempClock.clockSeconds;
			this.clock.clockMiliSecs = tempClock.clockMiliSecs;
			clock.pauseFlag = 1;
			this.repaint();	
			
			undoCheck = 1;
			pause.setText("Resume");
			this.addKeyListener(paddle);
			observable = new BreakoutObservable(paddle, ball);
			}
		}
		
		else if(e.getSource() == replay)
		{
			logger.debug("Replay Started");
			StartGame = 0;
			breakLoop = 1;
			undoCheck = 1;
			pause.setText("Resume");
			replayCheck = 1;			
		}

	}	
}
