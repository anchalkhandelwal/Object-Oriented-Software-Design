
public class BrickCommands implements Command {

	Brick brick;
	public BrickCommands(Brick brick) {
		// TODO Auto-generated constructor stub
		this.brick = brick;
	}
	
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		int brx = 0;
		int bry = 0;
		for (int i = 0; i < Constants.BRICK_ROW; i++) // put in
														// initializeBricks
		{
			for (int j = 0; j < Constants.BRICK_COLUMN; j++) {
				brick.setBricksX(i, j, brx+20);
				brick.setBricksY(i, j, bry+40);
				brx = brx + 100;
			}
			brx = 0;
			bry = bry + 40;
		}

	}

	@Override
	public Object undo() {
		// TODO Auto-generated method stub
		brick = Breakout.brickObjects.pop();
		return brick;
	}

	@Override
	public Object replay() {
		// TODO Auto-generated method stub
		brick = Breakout.brickQueue.get(Breakout.counter);
//		brick = Breakout.brickitr.next();
		return brick;
	}

}
