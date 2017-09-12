import java.awt.Color;
import java.awt.Dimension;

public class Constants {
	//Frame Constants
		protected static final int BOARD_HEIGHT = 700;
		protected static final int BOARD_WIDTH = 820;
		protected static final Dimension BOARD_DIMENSIONS= new Dimension(BOARD_HEIGHT, BOARD_WIDTH);	

		//Play start
		public static boolean PLAY = false;
		
		//Brick constants
		public static int BRICK_HEIGHT = 20;
		public static int BRICK_WIDTH = 75;
		public static int BRICK_ROW = 3;
		public static int BRICK_COLUMN = 10;
		public static int TOTAL_BRICKS = BRICK_ROW * BRICK_COLUMN;
		public static Color BRICK_COLOR = Color.ORANGE;  
		
		//Ball Constants
		public static int BALL_POS_X = 120; 	//X-coordinate position of ball
		public static int BALL_POS_Y = 200;	//Y-coordinate position of ball
		public static int BALL_VEL_X = 10;	//Velocity on X-axis
		public static int BALL_VEL_Y = 10;	//Velocity on Y-axis
		public static Color BALL_COLOR = Color.RED;  //Ball Color
		public static int BALL_WIDTH = 25;
		public static int BALL_HEIGHT = 25;
		
		//Paddle Constants
		public static int PADDLE_POS_X = 300;
		public static int PADDLE_POS_Y = BOARD_HEIGHT - 60;
		public static int PADDLE_WIDTH = 150;
		public static int PADDLE_HEIGHT = 20;
		public static Color PADDLE_COLOR = Color.GREEN;
		
		//Notifications
		public static int GAMEOVER_POS_X= 220;
		public static int GAMEOVER_POS_Y= 300;
		
		public static int WIN_POS_X= 200;
		public static int WIN_POS_Y= 300;
		
		int getPosX()
		{
			return BALL_POS_X;
		}
		
}
