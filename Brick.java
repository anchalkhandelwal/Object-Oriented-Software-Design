import java.awt.Color;
import java.awt.Graphics2D;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Brick implements Sprite, Cloneable, Serializable {
	
	int[][] bricksX;
	int[][] bricksY;
	static final int BRICK_WIDTH = Constants.BRICK_WIDTH;
	static final int BRICK_HEIGHT = Constants.BRICK_HEIGHT;
	Brick cloneBrick;
	
	Brick(Brick brick)
	{
		cloneBrick = brick;
	}
	
	public Brick(int a) {
		if (a == 1) {
			bricksX = new int[Constants.BRICK_ROW][Constants.BRICK_COLUMN];
			bricksY = new int[Constants.BRICK_ROW][Constants.BRICK_COLUMN];
			BrickCommands brickCommands = new BrickCommands(this);
			brickCommands.execute();
		}
	}

	public Object clone() throws CloneNotSupportedException 
	{
		Brick clone = new Brick(0);
		clone.bricksX = this.bricksX.clone();
		clone.bricksY = this.bricksY.clone();
		return clone;
	}
	
	public Object copy(Object brick) {
        Object obj = null;
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(byteArrayOutputStream);
            out.writeObject(brick);
            out.flush();
            out.close();

            ObjectInputStream in = new ObjectInputStream(
                new ByteArrayInputStream(byteArrayOutputStream.toByteArray()));
            obj = in.readObject();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        catch(ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
        }
        return obj;
    }
	
	
	public boolean bottomCollision(int xBall, int yBall, int xBrick, int yBrick) 
	{
		if ((xBall+25 >= xBrick) && (xBall <= xBrick + BRICK_WIDTH) && (yBall == yBrick + BRICK_HEIGHT)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean topCollision(int xBall, int yBall, int xBrick, int yBrick) {
		if ((xBall+25 >= xBrick) && (xBall <= xBrick + BRICK_WIDTH) && (yBall == yBrick - 25)) {
				return true;
			}
		else
			return false;
		
	}
	
	public boolean leftCollision(int xBall, int yBall, int xBrick, int yBrick) {
		if ((yBall+25 >= yBrick) && (yBall <= yBrick + BRICK_HEIGHT) && (xBall+25 == xBrick)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean rightCollision(int xBall, int yBall, int xBrick, int yBrick) {
		if ((yBall+25 >= yBrick) && (yBall <= yBrick + BRICK_HEIGHT) && (xBall == xBrick + BRICK_WIDTH)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public void brickCollide(Ball ball) 
	{
		for(int i = 0; i < Constants.BRICK_ROW; i++)
		{
			for(int j = 0; j < Constants.BRICK_COLUMN; j++)
			{
				if(bottomCollision(ball.getBx(), ball.getBy(), bricksX[i][j], bricksY[i][j])) {
					ball.setMoveY(Constants.BALL_VEL_Y);	
					bricksX[i][j] = -1;
					bricksY[i][j] = -1;
					
				}
				
				if(topCollision(ball.getBx(), ball.getBy(), bricksX[i][j], bricksY[i][j])) {
					ball.setMoveY(-(Constants.BALL_VEL_Y));
					bricksX[i][j] = -1;
					bricksY[i][j] = -1;
				}
				
				if(rightCollision(ball.getBx(), ball.getBy(), bricksX[i][j], bricksY[i][j])) {
					ball.setMoveX(-ball.getMoveX());
					bricksX[i][j] = -1;
					bricksY[i][j] = -1;
				}
				
				if(leftCollision(ball.getBx(), ball.getBy(), bricksX[i][j], bricksY[i][j])) {
					ball.setMoveX(-ball.getMoveX());
					bricksX[i][j] = -1;
					bricksY[i][j] = -1;
				}
			}
		}
	}

	@Override
	public void draw(Graphics2D g2d) {
		// TODO Auto-generated method stub
		for(int i = 0; i < Constants.BRICK_ROW; i++)
		{
			for(int j = 0; j < Constants.BRICK_COLUMN; j++)
			{
				if(bricksX[i][j] != -1 && bricksX[i][j] != -1)
				{
					g2d.setColor(Constants.BRICK_COLOR);
					g2d.fillRect(bricksX[i][j], bricksY[i][j], BRICK_WIDTH, BRICK_HEIGHT);
				}
			}
		}
	}
	
	int[][] getBricksX()
	{
		return bricksX;
	}
	
	int[][] getBricksY()
	{
		return bricksY;
	}
	
	void setBricksX(int i, int j, int x)
	{
		bricksX[i][j] = x;
	}
	
	void setBricksY(int i, int j, int x)
	{
		bricksY[i][j] = x;
	}
	
}
