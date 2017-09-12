
public class PaddleCommands implements Command {

	Paddle paddle;
	public PaddleCommands(Paddle paddle) {
		// TODO Auto-generated constructor stub
		this.paddle = paddle;
	}
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		if(paddle.getPx() != 0)
		{
			if (paddle.getKey() == 37) {
				// moving the paddle left
				paddle.setPx(paddle.getPx() - 50);
				
			}
		}
		if(paddle.getPx() != (Constants.BOARD_WIDTH - 170))
		{
			if (paddle.getKey() == 39) {
				// moving the paddle right
				paddle.setPx(paddle.getPx() + 50);
			}
		
		}
		
	}

	@Override
	public Object undo() {
		// TODO Auto-generated method stub
		paddle = Breakout.paddleObjects.pop();
		return paddle;
	}

	@Override
	public Object replay() {
		// TODO Auto-generated method stub
		//paddle = Breakout.paddleQueue.poll();
		paddle = Breakout.paddleQueue.get(Breakout.counter);
		return paddle;
	}

}
