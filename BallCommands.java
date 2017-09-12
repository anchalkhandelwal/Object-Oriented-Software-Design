
public class BallCommands implements Command 
{
	Ball ball;
	public BallCommands(Ball ball) {
		// TODO Auto-generated constructor stub
		this.ball = ball;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		if(ball.getBx() == 0){
			ball.setMoveX(Constants.BALL_VEL_X);
		}
		if(ball.getBx() == (Constants.BOARD_WIDTH - 50)){
			ball.setMoveX(-(Constants.BALL_VEL_X));
		}
		if(ball.getBy() == 0){
			ball.setMoveY(Constants.BALL_VEL_Y);
		}
		if(ball.getBy() == Constants.BOARD_HEIGHT){
			Breakout.gameIsOn = false;
		}
	}

	@Override
	public Object undo() {
		ball = Breakout.ballObjects.pop();
		return ball;
	}

	@Override
	public Object replay() {
		// TODO Auto-generated method stub
		ball = Breakout.ballQueue.get(Breakout.counter);
		return ball;
	}

}
