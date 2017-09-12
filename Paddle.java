import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Paddle implements KeyListener, Sprite, Cloneable
{
	int px = Constants.PADDLE_POS_X;
	int py = Constants.PADDLE_POS_Y;
	final int PADDLE_WIDTH = Constants.PADDLE_WIDTH;
	final int PADDLE_HEIGHT = Constants.PADDLE_HEIGHT;
	int key;

	Paddle clonePaddle;
	
	Paddle(int px, int py)
	{
		this.px = px;
		this.py = py;
	}
	
	Paddle(Paddle paddle) 
	{
		clonePaddle = paddle;
	}

	public Object clone() throws CloneNotSupportedException
	{
		return super.clone();	
	}
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		key = arg0.getKeyCode();
		PaddleCommands paddleCommands = new PaddleCommands(this);
		paddleCommands.execute();
	}
	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void draw(Graphics2D g2d) {
		g2d.setColor(Constants.PADDLE_COLOR);
        g2d.fillRect(px,py,PADDLE_WIDTH,PADDLE_HEIGHT);
	}
	
	int getPx()
	{
		return px;
	}
	
	int getPy()
	{
		return py;
	}
	
	void setPx(int x)
	{
		px = x;
	}
	
	void setPy(int y)
	{
		py = y;
	}
	
	int getPaddleWidth()
	{
		return PADDLE_WIDTH;
	}
	
	int getPaddleHeight()
	{
		return PADDLE_HEIGHT;
	}

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	
}
