import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;


public class Ball implements Observer, Sprite, Cloneable, Serializable

{
	int bx = Constants.BALL_POS_X;
	int by = Constants.BALL_POS_Y;
	int moveX = Constants.BALL_VEL_X;
	int moveY = Constants.BALL_VEL_Y;
	final int BALL_WIDTH = Constants.BALL_WIDTH;
	final int BALL_HEIGHT = Constants.BALL_HEIGHT;
	Ball cloneBall;
	
	Ball(int bx, int by, int moveX, int moveY)
	{
		this.bx = bx;
		this.by = by;
		this.moveX = moveX;
		this.moveY = moveY;
	}
	
	Ball(Ball ball)
	{
		cloneBall = ball;
	}
	
	Ball()
	{
		
	}
	
	public Object clone() throws CloneNotSupportedException
	{
		return super.clone();	
	}	
	
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		HashMap<String, Object> objects = (HashMap<String, Object>)arg;
		Ball ball = (Ball) objects.get("Ball");
		Paddle paddle = (Paddle) objects.get("Paddle");
		
		BallCommands ballCommands = new BallCommands(ball);
		ballCommands.execute();
		
		if((ball.getBx() >= paddle.getPx()) && (ball.getBx() <= paddle.getPx() + paddle.getPaddleWidth())
				&& (ball.getBy() + BALL_HEIGHT >= paddle.getPy()) && ( ball.getBy() <= ball.getBy() + paddle.getPaddleHeight()))
			ball.setMoveY(-(Constants.BALL_VEL_Y));
		
	}
	
	public void registerBall()
	{
		BreakoutObservable oberservable = new BreakoutObservable();
		oberservable.addObserver(this);
	}	
	
	public void unregisterBall()
	{
		BreakoutObservable observable = new BreakoutObservable();
		observable.removeObserver(this);
	}
	
	public void moveBall()
	{
		bx = bx + moveX;
		by = by + moveY;
	}

	@Override
	public void draw(Graphics2D g2d) {
		// TODO Auto-generated method stub
		g2d.setColor(Constants.BALL_COLOR);
        g2d.fillOval(bx, by, BALL_WIDTH, BALL_HEIGHT);
	}
	
	int getBx()
	{
		return bx;
	}
	
	int getBy()
	{
		return by;
	}
	
	void setBx(int x)
	{
		bx = x;
	}
	
	void setBy(int y)
	{
		by = y;
	}
	
	int getMoveX()
	{
		return moveX;
	}
	
	void setMoveX(int x)
	{
		moveX = x;
	}
	
	int getMoveY()
	{
		return moveY;
	}
	
	void setMoveY(int y)
	{
		moveY = y;
	}

}
