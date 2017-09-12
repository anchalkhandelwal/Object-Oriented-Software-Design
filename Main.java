import java.awt.Color;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import org.apache.log4j.Logger;


public class Main
{
	static Breakout breakout;
	final static Logger logger = Logger.getLogger(Main.class);

	
	public static void main(String args[])
	{
//		final int FRAME_WIDTH = 1920;
//		final int FRAME_HEIGHT = 1030;
		logger.debug("Testing to check if log4j working fine");
		
		final int FRAME_WIDTH = Constants.BOARD_WIDTH;
		final int FRAME_HEIGHT = Constants.BOARD_HEIGHT;
		JFrame frame = new JFrame();
		frame.setSize(Constants.BOARD_WIDTH, Constants.BOARD_HEIGHT);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Breakout!");
		
		Ball ball = new Ball(Constants.BALL_POS_X, Constants.BALL_POS_Y, Constants.BALL_VEL_X, Constants.BALL_VEL_Y);
		Paddle paddle = new Paddle(Constants.PADDLE_POS_X, Constants.PADDLE_POS_Y);
		Brick brick = new Brick(1);
		Clock clock = new Clock();
		
		breakout = new Breakout(ball, paddle, brick, clock);
		breakout.setSize(FRAME_WIDTH,FRAME_HEIGHT);
		frame.add(breakout);
		//frame.setBackground(Color.YELLOW);

		frame.setVisible(true);
		breakout.startGame();
		
	}
}
